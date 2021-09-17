public class Employee 
{
    int type;
    int id;

    String name;
    String city;

    int schedule;
    int payment_method;
    double salary;

    boolean sindicate;
    Sindicate sindicate_data = new Sindicate();

    public static void Taxa(Employee aux)//jogar pra sindicato
    {
        System.out.print("Indique o dia [dd]: ");
        int dia = Validar.dia_valido();

        System.out.print("Indique a taxa    : R$ ");
        double taxa = Validar.verifica_dinheiro();

        Sindicate.adiciona_taxa(aux, dia, taxa);
    }

    public static void change_name(Employee aux)
    {
        System.out.print("Digite o novo nome: ");
        String novo = Validar.string_valida();

        aux.name = novo;
    }

    public static void change_city(Employee aux)
    {
        System.out.print("Digite a nova cidade: ");
        String novo = Validar.string_valida();

        aux.city = novo;
    }

    public static void change_paymentmethod(Employee aux)
    {
        int novo;
        while(true)
        {
            System.out.print("Digite o Metodo de pagamento\n");
            System.out.print("Cheque correio[1] Cheque-Mãos[2] Deposito[3]: ");
            novo = Validar.int_valido();

            if(novo >= 1 && novo <= 3)
            {
                aux.payment_method = novo;
                break;
            }

        }

        Main.clear();
    }

    public static void change_sindicatemember(Employee aux)
    {
        System.out.print("Pertence ao sindicato? Sim[1] Não[2]");
        boolean novo = Validar.verifica_boolean();

        aux.sindicate = novo;
    }

    public static void change_sindicateid(Employee aux)
    {
        System.out.print("Digite a nova ID do sindicato: ");
        int novo = Validar.int_valido();

        aux.sindicate_data.sindicate_id = novo;
    }

    public static void change_sindicatetax(Employee aux)
    {
        System.out.print("Digite a nova taxa do sindicato: R$ ");
        double novo = Validar.verifica_dinheiro();

        aux.sindicate_data.mensal_taxe = novo;
    }
    public static void change_schedule(Employee aux)
    {
        int tipo;

        while(true)
        {
            System.out.print("Digite a agenda de pagamento\n");
            Functions.listofschedule.print();
            tipo = Validar.int_valido();

            if(tipo >= 0 && tipo < Functions.listofschedule.methods.size())
            {
                if(tipo != aux.schedule)
                {
                    aux.schedule = tipo;
                    return;
                }

                Main.clear();
            }
        }
    }
} 