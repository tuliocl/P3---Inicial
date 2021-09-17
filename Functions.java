import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Functions 
{
    public static Scanner input = new Scanner(System.in).useLocale(Locale.ENGLISH);

    int id = 1;
    int id_sindicato = 1;

    ArrayList<Hourly> listofhourly = new ArrayList<Hourly>();//lista de funcionarios - Horista
    ArrayList<Salaried> listofsalaried = new ArrayList<Salaried>();//lista de funcionarios - assalariado
    static PaymentSchedule listofschedule = new PaymentSchedule();

    ArrayList<UndoRedo> actions = new ArrayList<UndoRedo>();
    int current_action = 0;

    public void add()//Função de adicionar funcionario
    {
        UndoRedo new_action = new UndoRedo();
        UndoRedo.verify(actions,current_action);

        Main.clear();

        int type = Validar.tipo_funcionario();
        Main.clear();
        if(type == 1)//tipo horista
        {
            Hourly new_hourly = new Hourly();
            new_hourly = Hourly.add_hourly(new_hourly,id,id_sindicato);
            
            listofhourly.add(new_hourly);
            id++;

            if(new_hourly.sindicate)
            {
                id_sindicato++;
            }

            new_action.UndoRedo1(new_hourly,1);
            actions.add(new_action);
            current_action++;
        }

        else if(type == 2)//tipo assalariado
        {
            Salaried new_salaried = new Salaried();
    
            new_salaried = Salaried.add_Salaried(new_salaried,id,id_sindicato);
            
            listofsalaried.add(new_salaried);
            id++;

            if(new_salaried.sindicate)
            {
                id_sindicato++;
            }

            new_action.UndoRedo2(new_salaried,2);
            actions.add(new_action);
            current_action++;
        }
        
        Main.clear();     
    }
//--------------------------------------------------------------------
    public void remove() //Remove um usuario atraves de sua id
    {   
        UndoRedo new_action = new UndoRedo();
        UndoRedo.verify(actions,current_action);

        Main.clear();
        System.out.println("Lista de empregados: ");
        Validar.listhourly(listofhourly);
        Validar.listsalaried(listofsalaried);

        System.out.print("ID do empregado: ");
        int id = Validar.int_valido();
        
        int index_hourly = search_hourly(id, 1);
        if(index_hourly != -1)
        {
            Hourly aux = listofhourly.get(index_hourly);
            listofhourly.remove(index_hourly);

            System.out.println("Usuario Removido: " + aux.getNome() + " ID: " + aux.getId());
            System.out.println("Pressione ENTER para voltar ao menu\n");
            input.nextLine();

            Main.clear();

            new_action.UndoRedo1(aux,3);
            actions.add(new_action);
            current_action++;

            return;
        }

        int index_salaried = search_salaried(id, 1);
        if(index_salaried != -1)
        {
            Salaried aux1 = listofsalaried.get(index_salaried);
            listofsalaried.remove(index_salaried);

            System.out.println("Usuario Removido: " + aux1.getNome() + " ID: " + aux1.getId());
            System.out.println("Pressione ENTER para voltar ao menu\n");
            input.nextLine();

            Main.clear();

            new_action.UndoRedo2(aux1,4);
            actions.add(new_action);
            current_action++;

            return;
        }
    
        System.out.println("ID não encontrada!"); 
        System.out.print("Pressione ENTER para voltar ao menu\n");
        input.nextLine();
        
        Main.clear();
    }
//-------------------------------------------------------------------------------------------
    public void show_employees()
    {
        Main.clear();   

        System.out.println("HORISTAS:\n");
        for(int i = 0;i < listofhourly.size();i++)
        {
            Hourly aux = listofhourly.get(i);
          
            Hourly.print(aux);
            System.out.println();
            
            if(i + 1 == listofhourly.size())
            {
                System.out.println();
            }
       }

        System.out.printf("==================\n");

        System.out.println("ASSALARIADOS:\n");
        for(int i = 0;i < listofsalaried.size();i++)
        {
            Salaried aux = listofsalaried.get(i);
            
            Salaried.print(aux);
            System.out.println();
           
            if(i + 1 == listofsalaried.size())
            {
                System.out.println();
            }
        }
        
        System.out.println("Pressione ENTER para voltar ao menu\n");
        input.nextLine();
        Main.clear(); 
    }

//----------------------------------------------------------------------

    public void time_card()//Adiciona cartao de ponto a um horista
    {
        UndoRedo new_action = new UndoRedo();
        UndoRedo.verify(actions,current_action);

        Main.clear();
        System.out.println("Lista de empregados: ");
        Validar.listhourly(listofhourly);

        System.out.print("Digite a ID do horista: ");
        int id = Validar.int_valido();

        Hourly aux;
        int index = search_hourly(id, 1);

        if(index != -1)
        {
            aux = listofhourly.get(index);

            new_action.UndoRedo1(aux,5);
            actions.add(new_action);
            current_action++;

            Hourly.add_Timecard(aux);

            new_action.setAtt_hourly(aux);
        }

        else
        {
            System.out.println("ID nao encontrada");
            System.out.println("Pressione ENTER para voltar ao menu\n");
            input.nextLine();
            Main.clear(); 
        }
    }
//---------------------------------------------------
    public void sale()//adiciona uma venda a um assalariado
    {
        UndoRedo new_action = new UndoRedo();
        UndoRedo.verify(actions,current_action);
    
        Main.clear();
        System.out.println("Lista de empregados: ");
        Validar.listsalaried(listofsalaried);

        System.out.print("Digite a ID do assalariado: ");
        int id = Validar.int_valido();

        Salaried aux;
        int index = search_salaried(id, 1);
        
        if(index != -1)
        {
            aux = listofsalaried.get(index);

            new_action.UndoRedo2(aux,6);
            actions.add(new_action);
            current_action++;

            Boolean adicionou =  Salaried.add_sale(aux);
            if(adicionou == false)
            {
                actions.remove(new_action);
                current_action--;
            }
            else
            {
                new_action.setAtt_salaried(aux);
            }
        }

        else
        {
            System.out.println("ID nao encontrada");
            System.out.println("Pressione ENTER para voltar ao menu\n");
            input.nextLine();
            Main.clear();   
        }
    }

    public void tax()//lança uma taxa de serviço a um membro do sindicato
    {
        UndoRedo new_action = new UndoRedo();
        UndoRedo.verify(actions,current_action);
        Main.clear();

        System.out.print("Indique a ID do sindicato: ");
        int id = Validar.int_valido();

        Hourly hourly;
        Salaried salaried;

        int index = search_hourly(id, 2);

        if(index != -1)//achei horista
        {
            hourly = listofhourly.get(index);

            new_action.UndoRedo1(hourly,7);
            actions.add(new_action);
            current_action++;

            Employee.Taxa(hourly);

            new_action.setAtt_hourly(hourly);

            Main.clear();   
            return;
        }

        index = search_salaried(id, 2);

        if(index != -1)//achei assalarido
        {
            salaried = listofsalaried.get(index);

            new_action.UndoRedo2(salaried,8);
            actions.add(new_action);
            current_action++;

            Employee.Taxa(salaried);

            new_action.setAtt_salaried(salaried);

            Main.clear();  
            return;
        }

        System.out.println("ID nao encontrada");
        System.out.println("Pressione ENTER para voltar ao menu\n");
        input.nextLine();
        Main.clear();   
    }

    // TIPO = 1 => ID DO SISTEMA  || TIPO = 2 => ID DO SINDICATO
    private int search_hourly(int id,int tipo)
    {
        Hourly aux;
        for(int i = 0;i < listofhourly.size();i++)
        {
            aux = listofhourly.get(i);
            if(tipo == 1)
            {
                if(aux.id == id)
                {
                    return i;
                }
            }

            else if(tipo == 2 && aux.sindicate)
            {
                if(aux.sindicate_data.sindicate_id == id)
                {
                    return i;
                }
            }
        }

        return -1;
    }

    private int search_salaried(int id,int tipo)
    {
        Salaried aux;
        for(int i = 0;i < listofsalaried.size();i++)
        {
            aux = listofsalaried.get(i);
            if(tipo == 1)
            {
                if(aux.id == id)
                {
                    return i;
                }
            }

            else if(tipo == 2 && aux.sindicate)
            {
                if(aux.sindicate_data.sindicate_id == id)
                {
                    return i;
                }
            }
        }

        return -1;
    }

    public void change_data()//Altera dados de um usuario
    {
        Main.clear();
        System.out.println("Lista de empregados: ");
        Validar.listhourly(listofhourly);
        Validar.listsalaried(listofsalaried);

        System.out.print("Indique a ID do empregado: ");
        int id = Validar.int_valido();

        int index = search_hourly(id, 1);

        if(index != -1)//achei horista
        {
            Hourly hourly = listofhourly.get(index);

            modificate_data(hourly,id,1);
            Main.clear();   
            return;
        }

        index = search_salaried(id, 1);

        if(index != -1)//achei assalarido
        {
            Salaried salaried = listofsalaried.get(index);
            modificate_data(salaried,id,2);
            Main.clear();  
            return;
        }

        System.out.println("ID nao encontrada");
        System.out.println("Pressione ENTER para voltar ao menu\n");
        input.nextLine();
        Main.clear();   
    }

    private int menu_changes()
    {
        int escolha;

        while(true)
        {
            System.out.print("Nome                             [1]\n");
            System.out.print("Cidade                           [2]\n");
            System.out.print("Metodo de pagamento              [3]\n");
            System.out.print("Membro do sindicato              [4]\n");
            System.out.print("ID do sindicato                  [5]\n");
            System.out.print("Taxa do sindicato                [6]\n");
            System.out.print("Alterar agenda                   [7]\n");
            System.out.print("Alterar tipo de funcionario      [8]\n");

            System.out.printf("Escolha a opção: ");
            escolha = Validar.int_valido();
            
            if(escolha >= 1 && escolha <= 8)
            {
                Main.clear();
                break;
            }

            Main.clear();
        }

        return escolha;
    }

    private void modificate_data(Employee mudar,int id,int tipo)//chama as funções de alteração nas classes
    {
        int choice = menu_changes();

        UndoRedo new_action = new UndoRedo();
        UndoRedo.verify(actions,current_action);
        
        if(tipo == 1)
        {
            new_action.UndoRedo1((Hourly)mudar,9);
            actions.add(new_action);
            current_action++;
        }

        else if(tipo == 2)
        {
            new_action.UndoRedo2((Salaried)mudar,10);
            actions.add(new_action);
            current_action++; 
        }

        if(choice == 1)
        {
            Employee.change_name(mudar);
        }

        else if(choice == 2)
        {
            Employee.change_city(mudar);
        }

        else if(choice == 3)
        {
            Employee.change_paymentmethod(mudar);
        }

        else if(choice == 4)
        {
            Employee.change_sindicatemember(mudar);
            if(mudar.sindicate_data.sindicate_id == 0)
            {
                mudar.sindicate_data.sindicate_id = id_sindicato;
                id_sindicato++;

                System.out.println("Necessita alterar a taxa sindical");
            }
        }

        else if(choice == 5)
        {
            if(mudar.sindicate)
            {
                Employee.change_sindicateid(mudar);
            }
            else
            {
                System.out.print("Empregado não faz parte do sindicato\n");
            }
        }

        else if(choice == 6)
        {
            if(mudar.sindicate)
            {
                Employee.change_sindicatetax(mudar);
            }
            else
            {
                System.out.print("Empregado não faz parte do sindicato\n"); 
            }
        }  

        else if(choice == 7)
        {
            Employee.change_schedule(mudar);
        }  

        else if(choice == 8)
        {
            int new_type = Validar.tipo_funcionario();

            if(new_type == mudar.type)
            {
                System.out.print("O empregado já é desse tipo\n");
            }
    
            else
            {
                actions.remove(current_action - 1);
                current_action -= 1;

                if(tipo == 1)
                {
                    new_action.UndoRedo1((Hourly)mudar,11);
                    actions.add(new_action);
                    current_action++;
                }

                else if(tipo == 2)
                {
                    new_action.UndoRedo2((Salaried)mudar,12);
                    actions.add(new_action);
                    current_action++;
                }

                change_type(tipo,id,mudar,new_action);
            }
        }

        
        else
        {
            System.out.printf("Opção inválida!\n");
        }

        if(tipo == 1 && choice != 8)
        {
            new_action.setAtt_hourly((Hourly) mudar);
        }

        else if(tipo == 2 && choice != 8)
        {
            new_action.setAtt_salaried((Salaried) mudar);
        }

        System.out.println("Pressione ENTER para voltar ao menu\n");
        input.nextLine();
    
    }

    private void change_type(int tipo,int id,Employee mudar, UndoRedo new_action)
    {
        if(tipo == 1)//horista -> assalariado
        {
            Salaried novo = new Salaried();

            //copia os dados
            novo.type = 2;
            novo.id = mudar.id;
            novo.name = mudar.name;
            novo.city = mudar.city;
            novo.payment_method = mudar.payment_method;
            novo.schedule = mudar.schedule;
           
            System.out.print("Indique o salario: R$ ");
            novo.salary = Validar.verifica_dinheiro();
           
            novo.sindicate = mudar.sindicate;
            
            if(novo.sindicate)
            {
                novo.sindicate_data.sindicate_id = mudar.sindicate_data.sindicate_id;
                novo.sindicate_data.mensal_taxe = mudar.sindicate_data.mensal_taxe;

                for(int i = 0;i < mudar.sindicate_data.taxes.size(); i++) // passa as taxas
                {
                    Sale nova = mudar.sindicate_data.taxes.get(i);
                    novo.sindicate_data.taxes.add(nova);
                }
            }

            listofsalaried.add(novo);//adiciona na lista

            for(int i = 0;i < listofhourly.size();i++)//remove da lista
            {
                Hourly aux = listofhourly.get(i);
                if(aux.id == id)
                {
                    listofhourly.remove(i);
                    break;
                }
            }

            new_action.setAtt_salaried(novo);
        }

        else if(tipo == 2)//assalariado -> horista
        {
            //copia os dados
            Hourly novo = new Hourly();

            novo.type = 1;
            novo.id = mudar.id;
            novo.name = mudar.name;
            novo.city = mudar.city;
            novo.payment_method = mudar.payment_method;
            novo.schedule = mudar.schedule;

            System.out.print("Indique o salario por hora: R$ ");
            novo.salary = Validar.verifica_dinheiro();
           
            novo.sindicate = mudar.sindicate;
           
            if(novo.sindicate)
            {
                novo.sindicate_data.sindicate_id = mudar.sindicate_data.sindicate_id;
                novo.sindicate_data.mensal_taxe = mudar.sindicate_data.mensal_taxe;

                for(int i = 0;i < mudar.sindicate_data.taxes.size(); i++) // passa as taxas
                {
                    Sale nova = mudar.sindicate_data.taxes.get(i);
                    novo.sindicate_data.taxes.add(nova);
                }
            }

            listofhourly.add(novo);//adiciona na lista

            for(int i = 0;i < listofsalaried.size();i++)//remove da lista
            {
                Salaried aux = listofsalaried.get(i);
                if(aux.id == id)
                {
                    listofsalaried.remove(i);
                    break;
                }
            }

            new_action.setAtt_hourly(novo);
        }
    }

    public void create_schedule()
    {
        listofschedule.CriarNovaAgenda();
    }

    public void pay_roll()
    {
        PayRoll.Pagamento(listofschedule,listofhourly,listofsalaried);
    }

    public void undo()
    {   
        Main.clear();
        if(current_action == 0)
        {
            System.out.println("Nenhuma ação feita");
            input.nextLine();
        }
        else
        {
            UndoRedo action = actions.get(current_action - 1);
            UndoRedo.Undo(action,listofhourly,listofsalaried);

            current_action -= 1;
            System.out.println(current_action);
        }
    }

    public void redo()
    {
        Main.clear();

        if(current_action == actions.size())
        {
            System.out.println("Nenhuma ação para refazer");
            input.nextLine();
        }

        else
        {
            UndoRedo action = actions.get(current_action);
            UndoRedo.redo(action, listofhourly, listofsalaried);
            current_action++;
        }
    }
}