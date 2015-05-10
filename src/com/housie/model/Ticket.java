package com.housie.model;


public class Ticket 
{
	private int ticketID;
	private String[][] numbers;

	public Ticket(){
		ticketID = -1;
		numbers = new String[3][9];

		for( int i=0; i<3; i++ )
			for( int j=0; j<9; j++ )
				numbers[i][j] = null;
	}

	public void setTicketID( int ticketID ){
		this.ticketID = ticketID;
	}

	public void setNumber( int row, int col, int number ){
			
			if(number==-1)
				numbers[row][col] = null;
			else
				numbers[row][col] = "" + number;
	}

	public int getTicketID(){
		return this.ticketID;
	}

	public String[][] getNumbers(){
		return this.numbers;
	}

	public void setNumbers( String[][] numbers ){
		try{
			if( numbers.length == 3 && numbers[0].length == 9 )
				this.numbers = numbers;
		}catch( Exception e ){}
	}

	public int getNumber( int row, int col ){
		if( numbers[row][col] != null )
			return Integer.parseInt( numbers[row][col] );
		return -1;
	}

	public String[] getRowNumbers( int row ){
		if( row < 0 || row >=3 )
			return null;
		return numbers[row];
	}

	public String[] getColumnNumbers( int col ){
		if( col < 0 || col >= 9 )
			return null;
		String cols[] = new String[3];
		cols[0] = numbers[0][col];
		cols[1] = numbers[1][col];
		cols[2] = numbers[2][col];

		return cols;
	}

	public int getNumberCountForRow( int row ){
		String[] rowNumbers = getRowNumbers( row );
		int count = 0;
		for( int j=0; j<rowNumbers.length; j++ )
			if( rowNumbers[j] != null )
				count++;
		return count;
	}

	public String toString(){
		String result = " --- --- --- --- --- --- --- --- ---\n";
	    result += " Ticket ID: " + this.ticketID + "\n\n";
		result += " --- --- --- --- --- --- --- --- ---\n";
		for( int i=0; i<3; i++ ){
			for( int j=0; j<9; j++ ){
				if( numbers[i][j] == null )
					result += "|   ";
				else
                  result += "|  " + Integer.parseInt(numbers[i][j]);
//				    result += "|  " + ExcelUtil.inputList.get(Integer.parseInt(numbers[i][j]));
			}
			result += "|\n --- --- --- --- --- --- --- --- ---\n";
		}
		return result;
	}

	public boolean isNumberValid( int col, int number ){
		for( int i=0; i<3; i++ )
			if( numbers[i][col] == null )
				continue;
			else
				if( numbers[i][col].equals( "" + number ) )
					return false;
		return true;
	}

	public void finalizeTicket(){
		for( int i=0; i<9; i++ ){
			String[] cols = getColumnNumbers( i );
			for( int m=0; m<cols.length-1; m++ ){
				for( int n=m+1; n<cols.length; n++ ){
					if( cols[m] == null || cols[n] == null ){
						continue;
					}
					else{
						if( Integer.parseInt( cols[m] ) > Integer.parseInt( cols[n] ) ){
							String temp = cols[m];
							setNumber( m, i, Integer.parseInt(cols[n]) );
							setNumber( n, i, Integer.parseInt(cols[m]) );
							cols[m] = cols[n];
							cols[n] = temp;
						}
					}
				}
			}
		}
	}

	public int[] getEmptyColumns(){
		int emptyCols[] = new int[9];
		int inDex = 0;
		for( int i=0; i<9; i++ ){
			String cols[] = getColumnNumbers( i );
			if( cols[0] == null && cols[1] == null && cols[2] == null )
			emptyCols[inDex++] = i;
		}
		int res[] = new int[inDex];
		for( int k = 0; k<inDex; k++ )
			res[k] = emptyCols[k];
		return res;
	}

	public int[] getFullColumns(){
		int fullCols[] = new int[9];
		int inDex = 0;
		for( int i=0; i<9; i++ ){
			String cols[] = getColumnNumbers( i );
			if( cols[0] != null && cols[1] != null && cols[2] != null )
			fullCols[inDex++] = i;
		}
		int res[] = new int[inDex];
		for( int k = 0; k<inDex; k++ )
			res[k] = fullCols[k];
		return res;
	}

	public void adjustValues(){

	}
}
