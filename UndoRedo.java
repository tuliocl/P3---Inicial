import java.util.ArrayList;
import java.util.Scanner;

public class UndoRedo 
{   
    public static Scanner input = new Scanner(System.in);

    int operation;

    Salaried salaried = new Salaried();
    Hourly hourly = new Hourly();

    Salaried att_salaried = new Salaried(); 
    Hourly att_hourly = new Hourly();

    public static void verify(ArrayList<UndoRedo> actions, int current_action)
    {
        if(current_action < actions.size())
        {
            for(int i = 0;i < actions.size();i++)
            {
                if(i > (current_action - 1))
                {
                    while(actions.size() != i)
                    {
                        actions.remove(i);
                    }
                }
            }
        }
    }

    public void UndoRedo1(Hourly save_hourly,int operation)
    {
        this.operation = operation;
        this.hourly = Hourly.clone(save_hourly);
    }

    public void UndoRedo2(Salaried save_salaried,int operation)
    {
        this.operation = operation;
        this.salaried = Salaried.clone(save_salaried);
    }

    public void setAtt_salaried(Salaried att_salaried) {
        this.att_salaried = Salaried.clone(att_salaried);
    }

    public void setAtt_hourly(Hourly att_hourly) {
        this.att_hourly = Hourly.clone(att_hourly);
    }

    public static void Undo(UndoRedo action,ArrayList<Hourly> listofhourly, ArrayList<Salaried> listofsalaried)
    {
        if(action.operation == 1)
        {
           undo_remove_current_hourly(action.hourly,listofhourly);
           System.out.println("Operação (ADD HORISTA) desfeita\nENTER para voltar");
        }

        else if(action.operation == 2)
        {
            undo_remove_current_salaried(action.salaried,listofsalaried);
            System.out.println("Operação (ADD SALARIADO) desfeita\nENTER para voltar");
        }
        
        else if(action.operation == 3)
        {
            undo_add_previous_hourly(action.hourly,listofhourly);
            System.out.println("Operação (REMOVE HORISTA) desfeita\nENTER para voltar");
        }

        else if(action.operation == 4)
        {
            undo_add_previous_salaried(action.salaried,listofsalaried);
            System.out.println("Operação (REMOVE SALARIADO) desfeita\nENTER para voltar");
        }

        else if(action.operation == 5)
        {
            undo_remove_current_hourly(action.hourly,listofhourly);
            undo_add_previous_hourly(action.hourly,listofhourly);
            System.out.println("Operação (ADD Cartão de ponto) desfeita\nENTER para voltar");
        }

        else if(action.operation == 6)
        {
            undo_remove_current_salaried(action.salaried, listofsalaried);
            undo_add_previous_salaried(action.salaried, listofsalaried);
            System.out.println("Operação (ADD Venda) desfeita\nENTER para voltar");
        }
        
        else if(action.operation == 7)
        {
            undo_remove_current_hourly(action.hourly,listofhourly);
            undo_add_previous_hourly(action.hourly,listofhourly);
            System.out.println("Operação (ADD Taxa) desfeita\nENTER para voltar");
        }

        else if(action.operation == 8)
        {
            undo_remove_current_salaried(action.salaried, listofsalaried);
            undo_add_previous_salaried(action.salaried, listofsalaried);
            System.out.println("Operação (ADD Taxa) desfeita\nENTER para voltar");
        }

        else if(action.operation == 9)
        {
            undo_remove_current_hourly(action.hourly,listofhourly);
            undo_add_previous_hourly(action.hourly,listofhourly);
            System.out.println("Operação (Alterar dados) desfeita\nENTER para voltar");
        }

        else if(action.operation == 10)
        {
            undo_remove_current_salaried(action.salaried, listofsalaried);
            undo_add_previous_salaried(action.salaried, listofsalaried);
            System.out.println("Operação (Alterar dados) desfeita\nENTER para voltar");
        }

        else if(action.operation == 11)
        {
            int id = action.hourly.id;
            undo_remove_salaried_using_id(id,listofsalaried);
            undo_add_previous_hourly(action.hourly, listofhourly);
            System.out.println("Operação (Alterar dados) desfeita\nENTER para voltar");
        }

        else if(action.operation == 12)
        {
            int id = action.salaried.id;
            undo_remove_hourly_using_id(id,listofhourly);
            undo_add_previous_salaried(action.salaried, listofsalaried);
            System.out.println("Operação (Alterar dados) desfeita\nENTER para voltar");
        }

        input.nextLine();
        Main.clear();
    }

    public static void redo(UndoRedo action,ArrayList<Hourly> listofhourly, ArrayList<Salaried> listofsalaried)
    {
        if(action.operation == 1)
        {
            undo_add_previous_hourly(action.hourly, listofhourly);
            System.out.println("Operação (ADD HORISTA) refeita\nENTER para voltar");
        }

        else if(action.operation == 2)
        {
            undo_add_previous_salaried(action.salaried, listofsalaried);
            System.out.println("Operação (ADD SALARIADO) refeita\nENTER para voltar");
        }
        
        else if(action.operation == 3)
        {
            undo_remove_current_hourly(action.hourly, listofhourly);
            System.out.println("Operação (REMOVE HORISTA) refeita\nENTER para voltar");
        }

        else if(action.operation == 4)
        {
            undo_remove_current_salaried(action.salaried,listofsalaried);
            System.out.println("Operação (REMOVE SALARIADO) refeita\nENTER para voltar");
        }

        else if(action.operation == 5)
        {
            undo_remove_current_hourly(action.hourly, listofhourly);
            undo_add_previous_hourly(action.att_hourly, listofhourly);
            System.out.println("Operação (ADD Cartão de ponto) refeita\nENTER para voltar");
        }

        else if(action.operation == 6)
        {
            undo_remove_current_salaried(action.salaried, listofsalaried);
            undo_add_previous_salaried(action.att_salaried, listofsalaried);
            System.out.println("Operação (ADD Venda) refeita\nENTER para voltar");
        }
        
        else if(action.operation == 7)
        {
            undo_remove_current_hourly(action.hourly, listofhourly);
            undo_add_previous_hourly(action.att_hourly, listofhourly);
            System.out.println("Operação (ADD Taxa) refeita\nENTER para voltar");
        }

        else if(action.operation == 8)
        {
            undo_remove_current_salaried(action.salaried, listofsalaried);
            undo_add_previous_salaried(action.att_salaried, listofsalaried);
            System.out.println("Operação (ADD Taxa) refeita\nENTER para voltar");
        }

        else if(action.operation == 9)
        {
            undo_remove_current_hourly(action.hourly, listofhourly);
            undo_add_previous_hourly(action.att_hourly, listofhourly);
            System.out.println("Operação (Alterar dados) refeita\nENTER para voltar");
        }

        else if(action.operation == 10)
        {
            undo_remove_current_salaried(action.salaried, listofsalaried);
            undo_add_previous_salaried(action.att_salaried, listofsalaried);
            System.out.println("Operação (Alterar dados) refeita\nENTER para voltar");
        }

        else if(action.operation == 11)
        {
            undo_remove_current_hourly(action.hourly, listofhourly);
            undo_add_previous_salaried(action.att_salaried, listofsalaried);
            System.out.println("Operação (Alterar dados) refeita\nENTER para voltar");
        }

        else if(action.operation == 12)
        {
            undo_remove_current_salaried(action.salaried, listofsalaried);
            undo_add_previous_hourly(action.att_hourly, listofhourly);
            System.out.println("Operação (Alterar dados) refeita\nENTER para voltar");
        }

        input.nextLine();
        Main.clear();
    }

    private static void undo_remove_current_hourly(Hourly toremove, ArrayList<Hourly> list)//remove
    {
        for(int i = 0;i < list.size();i++)
        {
            Hourly aux = list.get(i);
            if(toremove.id == aux.id)
            {
                list.remove(i);
            }
        }
    }

    private static void undo_remove_current_salaried(Salaried toremove, ArrayList<Salaried> list)
    {
        for(int i = 0;i < list.size();i++)
        {
            Salaried aux = list.get(i);
            if(toremove.id == aux.id)
            {
                list.remove(i);
            }
        }
    }

    private static void undo_add_previous_hourly(Hourly toadd, ArrayList<Hourly> list)
    {
        list.add(toadd);
    }

    private static void undo_add_previous_salaried(Salaried toadd, ArrayList<Salaried> list)
    {
        list.add(toadd);
    }

    private static void undo_remove_salaried_using_id(int id, ArrayList<Salaried> list)
    {
        for(int i = 0;i < list.size();i++)
        {
            Salaried aux = list.get(i);
            if(id == aux.id)
            {
                list.remove(i);
            }
        }
    }

    private static void undo_remove_hourly_using_id(int id, ArrayList<Hourly> list)
    {
        for(int i = 0;i < list.size();i++)
        {
            Hourly aux = list.get(i);
            if(id == aux.id)
            {
                list.remove(i);
            }
        }
    }
}