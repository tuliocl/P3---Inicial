    import java.util.ArrayList;

public class Salaried extends Employee
{
    boolean iscommissioned;
    double comission;
    ArrayList<Sale> sales = new ArrayList<Sale>();

    public static Salaried add_Salaried(Salaried novo,int id,int id_sindicato)
    {
        novo.type = 2;
        novo.id = id;

        System.out.print("Digite o nome: ");
        novo.name = Validar.string_valida();
        Main.clear();

        System.out.print("Digite a cidade: ");
        novo.city = Validar.string_valida();
        Main.clear();

        System.out.print("Digite o salario: R$ ");
        novo.salary = Validar.verifica_dinheiro();
        Main.clear();

        while(true)
        {
            System.out.print("Digite a Agenda de pagamento\n");
            Functions.listofschedule.print();
            System.out.print("Código da escolha: ");
            novo.schedule = Validar.int_valido();

            if(novo.schedule >= 0 && novo.schedule < Functions.listofschedule.methods.size())
            {
                Main.clear();
                break;
            }

            Main.clear();
        }

        while(true)
        {
            System.out.print("Digite o Metodo de pagamento\n");
            System.out.print("Cheque correio[1] Cheque-Mãos[2] Deposito[3]: ");
            System.out.print("Código da escolha: ");
            novo.payment_method = Validar.int_valido();

            if(novo.payment_method >= 1 && novo.payment_method <= 3)
            {
                Main.clear();
                break;
            }
            
            Main.clear();
        }

        System.out.print("O funcionario é comissionado? SIM[1] NÃO[2]: ");
        novo.iscommissioned = Validar.verifica_boolean();
        Main.clear();

        novo.comission = -1;

        if(novo.iscommissioned)
        {
            System.out.print("Digite a comissao: % ");
            novo.comission = Validar.verifica_dinheiro();
            Main.clear();
        }

        System.out.print("Pertence ao sindicato? Sim[1] Não[2]: ");
        novo.sindicate = Validar.verifica_boolean();
        Main.clear();

        if(novo.sindicate)
        {
            novo.sindicate_data.sindicate_id = id_sindicato;

            System.out.print("Digite a taxa sindical: R$ ");
           novo.sindicate_data.mensal_taxe = Validar.verifica_dinheiro();
           Main.clear();
        }

        return novo;
    }

    public static boolean add_sale(Salaried aux)
    {
        if(aux.iscommissioned == false)
        {
            System.out.println("O funcionário não é comissionado");
            return false;
        }

        System.out.print("Indique o dia da venda: ");
        int dia = Validar.dia_valido();

        System.out.print("Indique o valor da venda: R$");
        double valor = Validar.verifica_dinheiro();

        Sale nova = new Sale(dia,valor);
        aux.sales.add(nova);

        Main.clear();  
        return true;
    }

    public static double Total_sales(Salaried aux)
    {   
        double total = 0.0;
        for(int i = 0;i < aux.sales.size();i++)
        {   
            Sale sale = aux.sales.get(i);
            total += sale.value;
        }

        return total * (aux.comission/100);
    }
    public static void print(Salaried aux)
    {
        System.out.printf("NOME              : %s\n",aux.name);
        System.out.printf("CIDADE            : %s\n",aux.city);
        System.out.printf("SALARIO           : R$ %.2f\n",aux.salary);
       // System.out.printf("PAGAMENTO         : %s\n",Functions.listofschedule.methods.get(aux.schedule));
        if(aux.iscommissioned)
        {
            System.out.printf("COMISSAO          : %.2f\n",aux.comission);

            System.out.print("VENDAS FEITAS\n");
            for (int i = 0; i < aux.sales.size(); i++) 
            {
                Sale aux1 = aux.sales.get(i);
    
                System.out.printf("DIA %d: R$%.2f\n",aux1.day,aux1.value);
            }
        }
        
        if(aux.sindicate)
        {
            System.out.printf("TAXA SINDICAL     : R$ %.2f\n",aux.sindicate_data.mensal_taxe);
            System.out.printf("ID SINDICATO      : %d\n",aux.sindicate_data.sindicate_id);
            System.out.printf("TAXAS\n");

            for(int i = 0;i < aux.sindicate_data.taxes.size();i++)
            {
                Sale nova = aux.sindicate_data.taxes.get(i);
                System.out.printf("DIA %d: %.1f\n",nova.day,nova.value);
            }
        }
        System.out.printf("ID                : %d\n",aux.id);
    }

    public String getNome() {
        return name;
    }

    public int getId() {
        return id;
    }

    public static Salaried clone(Salaried aux)
    {
        Salaried clone = new Salaried();
        clone.type = 2;
        clone.id = aux.id;
        clone.name = aux.name;
        clone.city = aux.city;
        clone.payment_method = aux.payment_method;
        clone.schedule = aux.schedule;
        clone.salary = aux.salary;

        clone.sindicate = aux.sindicate;
            
        if(clone.sindicate)
        {
            clone.sindicate_data.sindicate_id = aux.sindicate_data.sindicate_id;
            clone.sindicate_data.mensal_taxe = aux.sindicate_data.mensal_taxe;

            for(int i = 0;i < aux.sindicate_data.taxes.size(); i++) // passa as taxas
            {
                Sale seila = aux.sindicate_data.taxes.get(i);
                Sale nova = new Sale();

                nova.day = seila.day;
                nova.value = seila.value;

                clone.sindicate_data.taxes.add(nova);
            }
        }
        

        clone.iscommissioned = aux.iscommissioned;
        if(clone.iscommissioned)
        {
            clone.comission = aux.comission;

            for(int i = 0;i < aux.sales.size(); i++) // passa as taxas
            {
                Sale seila = aux.sales.get(i);
                Sale nova = new Sale();
    
                nova.day = seila.day;
                nova.value = seila.value;
    
                clone.sales.add(nova);
            }
        }

        return clone;
    }
}