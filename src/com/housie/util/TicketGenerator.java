package com.housie.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import com.housie.model.InputData;
import com.housie.model.Ticket;

public class TicketGenerator
{
    public static PrintWriter generateSheets(InputData input, PrintWriter out) throws IOException
    {
        ExcelUtil.read();
        TicketGeneratorAlgo ticketGenerator = new TicketGeneratorAlgo();
        int numberOfPlayers = Integer.parseInt(input.getSheet());
        HtmlUtils hu = new HtmlUtils();
        out.print(hu.createHtmlHeader("--------------Tambola--------------------"));
        Vector av = new Vector();
        for (int player = 0; player < numberOfPlayers; player++)
        {
            Ticket ticket = ticketGenerator.generateTicket();
            System.out.println(ticket);

            out.print(hu.getTableHead("center", 1));
            av = new Vector();
            for (int i = 0; i < 3; i++)
            {
                for (int j = 0; j < 9; j++)
                {
                    if (ticket.getNumbers()[i][j] == null)
                    {
                        av.addElement("------");
                    }
                    else
                    {

                        int index=Integer.parseInt(ticket.getNumbers()[i][j]);
                        av.addElement(ExcelUtil.inputList.get(index-1));
                    }
                }
            }
            out.print(hu.getTableContents("center", av, 9,200));
            out.print(hu.getBR(25));
        }
        printAllInputs(input, out, hu);
        out.print(hu.getHtmlFooter());

        return out;
    }

    private static void printAllInputs(InputData input, PrintWriter out,HtmlUtils hu) throws IOException
    {
        out.print(hu.getTableHead("center", 1));
        Vector av = new Vector();
        for (String obj:ExcelUtil.inputList)
        {
                    av.addElement(obj);
        }
        out.print(hu.getTableContents("center", av, 10,50));
        out.print(hu.getBR(25));

    }
}
