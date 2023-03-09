import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class modelMashine {
    
    protected void load_data(Map<Drink,Integer> lst){
        lst.put(new Drink("кофе","Эспрессо",100,50,80), 10);
        lst.put(new Drink("кофе","Эспрессо",150,80,80), 10);
        lst.put(new Drink("кофе","Капучино",200,200,80), 10);
        lst.put(new Drink("кофе","Капучино",250,260,80), 10);        
        lst.put(new Drink("кофе","Латте Мокиато",120,150,80), 10);
        lst.put(new Drink("кофе","Латте Мокиато",250,200,80), 10);
        lst.put(new Drink("чай","черный",100,100,90), 2);
        lst.put(new Drink("чай","черный",150,150,90), 2);
        lst.put(new Drink("чай","Пуэр",100,200,90), 5);
        lst.put(new Drink("чай","Пуэр",140,280,90), 5);
        lst.put(new Drink("чай","Каркадэ",100,200,90), 5);
        lst.put(new Drink("чай","Каркадэ",150,260,90), 5);
        lst.put(new Drink("горячий шоколад","Шоколад",100,100,90), 6);
        lst.put(new Drink("горячий шоколад","Шоколад",200,200,90), 6);
    }
    protected Map<Integer,String> create_menu(String name,Map<Drink,Integer> lst){
        Map<Integer,String> menu = new HashMap<Integer,String>();
        Set<String> tmp = new HashSet<String>();
        int cnt=1;
        if(name.isEmpty())
        {
            for(var item:lst.entrySet())
            {   
                if(item.getValue()>0){
                    tmp.add(item.getKey().get_type());
                }
            }
        }
        else{
            for(var item:lst.entrySet()){
                if(item.getKey().get_type().compareToIgnoreCase(name)==0 && item.getValue()>0){
                    tmp.add(item.getKey().get_name());
                }
            }
        }

        for(String item:tmp)
        {
            menu.put(cnt, item);
            cnt++;
        }

        return menu;

    }
    
    
    protected String get_name(Map<Integer,String> lst,String back,String inp){
        String res = "";
        if(back.compareToIgnoreCase("0")==0 && inp.compareToIgnoreCase("0")==0)
            {
                return "-1";
            }
            
            if(lst.containsKey(Integer.parseInt(inp)))
            {
                res = lst.get(Integer.parseInt(inp));
            }
            return res;
    }

    protected Map<Integer,Drink> get_drinks(String drinkName, Map<Drink,Integer> lst){
        Map<Integer,Drink> result = new HashMap<Integer,Drink>();
        int cnt=1;
        
        for(var item:lst.entrySet()){
          
            if(item.getKey().get_name().compareToIgnoreCase(drinkName)==0 && item.getValue()>0){
                result.put(cnt, item.getKey());
                cnt++;
            }
        }
        
        return result;

    }

    protected Drink get_drink(Map<Integer,Drink> lst,String back,String inp)
    {
        Drink res = new Drink("tmp", "prev", 0, 0, 0);
        if(inp.compareToIgnoreCase(back)==0)
        {   
            res.set_name("back");
        }
        else{
            if(lst.containsKey(Integer.parseInt(inp)))
            {
                res = lst.get(Integer.parseInt(inp));
            }
        }
        
        return res;
    }

    protected boolean get_product(Map<Drink,Integer> lst,Drink drink){
        
        boolean res=false;
        for(var item:lst.entrySet())
        {
            if(item.getKey().compareTo(drink)==1)
            { 
                lst.replace(item.getKey(), lst.get(item.getKey()), lst.get(item.getKey())-1);
                res=true;
            }
        }
        return res;
    }
}
