package Server;

public class Book {

	private String name;
	private String author;
	private float price;
	
	public Book(String n, String a, float p)
	{
		name = n;
		author = a;
		price = p;
	}
	
	public String getBookName()
	{
		return name;
	}
	
	public String toString()
	{
		return name+"*"+author+"*"+price;
	}
}
