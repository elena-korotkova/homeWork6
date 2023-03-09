import java.util.HashMap;
import java.util.Map;

public class vendingMashine{
    private static vendingMashine mashine;
    private viewMashine view;
    private modelMashine model;
    private Map<Drink,Integer> drinkList;
    private Map<Integer,String> mainMenu;
    private Map<Integer,String> namesMenu;
    private Map<Integer,Drink> valuesMenu;

    private vendingMashine(){}

    public static vendingMashine init_mashine(){
        if(mashine==null)
        {
            mashine = new vendingMashine();
        }
        return mashine;
    }

    private void init_data(){
        this.model = new modelMashine();
        this.view = new viewMashine();
        this.drinkList = new HashMap<Drink,Integer>();    
        this.mainMenu = new HashMap<Integer,String>();
        this.namesMenu = new HashMap<Integer,String>();
        this.valuesMenu = new HashMap<Integer,Drink>();

        this.model.load_data(drinkList);
        this.view.show_welcome();
    }

    public void run(){
        this.init_data();

        boolean step2=false;
        while(true){
            step2=true;
            this.view.clear();
            mainMenu = (HashMap<Integer,String>)this.model.create_menu("",this.drinkList);
            this.view.show_menu("***** Меню напитков *****", mainMenu);
            String drinkType = this.user_input("Выберите напиток: ",mainMenu,"");
            if(drinkType.compareToIgnoreCase("")!=0){
                while (step2){
                    namesMenu = (HashMap<Integer,String>)this.model.create_menu(drinkType, this.drinkList);
                    this.view.clear();
                    this.view.show_menu("***** Меню напитков-1 *****", namesMenu);
                    
                    String drinkName = this.user_input("Выберите напиток (0-вернуться на шаг): ",namesMenu,"0");
                    System.out.println(drinkName);
                    if(drinkName.compareTo("-1")==0)
                    {
                        break;
                    }
                    if(drinkName.compareToIgnoreCase("")!=0){
                        while(true){
                            this.view.clear();
                            valuesMenu = this.model.get_drinks(drinkName, drinkList);
                            this.view.show_drinks("***** Меню напитков *****",valuesMenu);
                            Drink dr = this.get_drink(valuesMenu,"0");
                           
                            if(dr.get_name().compareToIgnoreCase("back")==0){
                                break;
                            }
                            if(dr.get_name().compareToIgnoreCase("prev")!=0){
                                this.get_product(dr);
                                step2=false;
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    private String user_input(String info,Map<Integer,String> lst,String back)
        {
            this.view.print(info);
            String input=this.view.get_user_data();
            return this.model.get_name(lst, back, input);
        }
    
    private Drink get_drink(Map<Integer,Drink> lst,String back){
    
        this.view.print("\nВыберите напиток (0-вернуться на шаг): ");
        String input=this.view.get_user_data();
        return this.model.get_drink(lst, back, input);

    }
    
    private void get_product(Drink drink){
        this.view.clear();
        
        if(this.model.get_product(this.drinkList, drink))
        {
            this.view.print("Идет приготовление напитка");
            this.view.printCheck(drink);
            drink.reset_iter();
            try{
                Thread.sleep(1500);
            }
            catch (InterruptedException ex) {
            }
        }

    }

}
