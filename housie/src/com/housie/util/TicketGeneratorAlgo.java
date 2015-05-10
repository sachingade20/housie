package com.housie.util;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import com.housie.model.Ticket;

class TicketGeneratorAlgo
{
    static int ticketCounter = 1;
    Set<String> numberset=new HashSet<String>();

    public Ticket generateTicket()
    {
        Ticket ticket = new Ticket();
        ticket.setTicketID(ticketCounter++);
        numberset=new HashSet<String>();
        Random rdNumber = new Random();
        int lastcolMax = -1;
        int colMax = rdNumber.nextInt(3);

        for (int i = 0; i < 9; i++)
        {
            while (lastcolMax == colMax)
                colMax = rdNumber.nextInt(3);

            lastcolMax = colMax;

            for (int j = 0; j < colMax; j++)
            {
                int number = getRandomNumber(i, ticket);
                
                int row = rdNumber.nextInt(3);

                while (ticket.getNumber(row, i) != -1)
                    row = rdNumber.nextInt(3);

                ticket.setNumber(row, i, number);
            }
        }
        if (!isTicketFilled(ticket))
            fillTicket(ticket);
        adjustTicket(ticket);
        ticket.finalizeTicket();
        return ticket;
    }

    public void adjustTicket(Ticket ticket)
    {
        int emptyCols[] = ticket.getEmptyColumns();
        int fullCols[] = ticket.getFullColumns();
        if (emptyCols.length == 0)
            return;
        if (emptyCols.length <= fullCols.length)
        {
            for (int i = 0; i < emptyCols.length; i++)
            {
                int row = (new Random()).nextInt(3);
                int number = getRandomNumber(emptyCols[i], ticket);
                ticket.setNumber(row, fullCols[i], -1);
                ticket.setNumber(row, emptyCols[i], number);
            }
        }
    }

    public void fillTicket(Ticket ticket)
    {
        Random rdNumber = new Random();
        int iterations1, iterations2;
        iterations1 = iterations2 = 0;
        for (int i = 0; i < 3; i++)
        {
            int count;
            boolean flag = false;
            if (ticket.getNumberCountForRow(i) >= 6)
            {
                while (ticket.getNumberCountForRow(i) != 5)
                {
                    int col = rdNumber.nextInt(9);
                    if (ticket.getNumber(i, col) != -1)
                        ticket.setNumber(i, col, -1);
                }
                continue;
            }
            while ((count = ticket.getNumberCountForRow(i)) <= 4)
            {
                int col = rdNumber.nextInt(9);
                while (ticket.getNumber(i, col) != -1)
                {
                    iterations2++;
                    col = rdNumber.nextInt(9);
                    if (iterations2 >= 100)
                    {
                        // System.out.println( iterations2 +
                        // ". Here i m stuck at Fill Ticket, dont know why col = " + col );
                        flag = true;
                        break;
                    }
                }
                if (flag)
                {
                    col = -1;
                    for (int col1 = 0; col < 9; col1++)
                        if (ticket.getNumber(i, col1) == -1)
                        {
                            col = col1;
                            break;
                        }
                    if (col == -1)
                    {
                        i++;
                        continue;
                    }
                }
                int number = getRandomNumber(col, ticket);
                ticket.setNumber(i, col, number);
                iterations1++;
                if (iterations1 == 50)
                {
                }
                // System.out.println( "Here i m stuck at Fill Ticket, dont know why11" );
            }
        }
    }

    public int getRandomNumber(int col, Ticket ticket)
    {
        int number = 0;
        Random rdNumber = new Random();
        int iterations = 0;
        int size=ExcelUtil.inputList.size();
        
        do
        {
            number = rdNumber.nextInt((col * 10) + 11);
            iterations++;
            if (iterations >= 50)
            {
            	number=1;
            	break;
            }
            if(number <=size-1&&!numberset.contains(number+"")&&number<(col*10+11)&&number>(col*10)){
                numberset.add(number+"");
            	break;
            }
        }
        while (true);
        return number;
    }

    boolean isTicketFilled(Ticket ticket)
    {
        boolean ticketFilled = true;
        for (int row = 0; row < 3; row++)
        {
            int count = ticket.getNumberCountForRow(row);
            if (count == 5)
                continue;
            else
                return false;
        }
        return ticketFilled;
    }

    public static void main(String args[])
    {
        ExcelUtil.read();
        TicketGeneratorAlgo ticketGenerator=new TicketGeneratorAlgo();
         System.out.println( ticketGenerator.generateTicket() );
        // System.out.println( ticketGenerator.generateTicket() );
        // System.out.println( ticketGenerator.generateTicket() );
    }
}
