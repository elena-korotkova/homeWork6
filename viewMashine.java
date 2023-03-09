import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class viewMashine {
    Scanner scan = new Scanner(System.in);
    
    protected void show_welcome(){
        this.print("\n***** Welcome to vendingMashine v1.1 *****\n");
    }

    protected void show_menu(String info,Map<Integer,String> menu){

        this.print("\n");
        this.print(info+"\n");
        this.print("\n");

        for(var item:menu.entrySet())
        {        
            this.print(item.getKey().toString());
            this.print(" - ");
            this.print(item.getValue()+"\n");
        }
        this.print("\n");
        
    }

    protected void printCheck(Drink drink){
        this.print("\nПечать чека.");
        System.out.println(drink.get_name());
        Iterator<String> drinkField = drink;
        while(drinkField.hasNext())
        {
            this.print(drinkField.next()+"\n");
        }
    }

    protected void clear(){
        this.print("\033[H\033[J");
    }

    protected void show_drinks(String info,Map<Integer,Drink> lst){
        this.print("\n");
        this.print(info+"\n");
        this.print("\n");

        this.print("   Наименование   объем   температура  цена\n");
        for(var item:lst.entrySet())
        {
            System.out.print(item.getKey());
            System.out.print(" - ");
            System.out.print(item.getValue().get_name());
            System.out.print("       ");
            System.out.print(item.getValue().get_volume());
            System.out.print("        ");
            System.out.print(item.getValue().get_temperature());
            System.out.print("      ");
            System.out.println(item.getValue().get_cost());
            
        }
    }

    protected String get_user_data()
    {
        return scan.next();
    }

    protected void print(String info)
    {
        System.out.print(info);
    }
}
