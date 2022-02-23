package fivetonine;

/**
 * Represents a user of fivetonine. Has all details of database version, except Password, which is never
 * pulled from database, only compared to database.
 * 
 * @author Alex Mailloux
 *
 */
public class User {
	int UID = 0;
	String Email = "";
	String Uname = "";
	String Street = "";
	String City = "";
	String State = "";
	int Zip = 0;
}
