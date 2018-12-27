package agh.cs.oop.kubicki;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            Database database = new Database(args[0],args[1]);
            if (args.length > 2){
                Console console = new Console(database,args[2]);
            }
            else{
                Console console = new Console(database,"");
            }
        }
        catch (ArrayIndexOutOfBoundsException ex){
            ex.printStackTrace();
            System.out.println("Błąd przekazania argumentów!");
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Błąd działania konsoli!");
            System.exit(1);
        }
    }
}