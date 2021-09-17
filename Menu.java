import java.util.Locale;
import java.util.Scanner;

public class Menu 
{
    Scanner input = new Scanner(System.in).useLocale(Locale.ENGLISH);//variavel de entrada
    Functions funcoes = new Functions();

    public void Start()
    {
        System.out.println("Bem vindo ao sistema!!!");
        while(true)
        {
            System.out.println("Por favor, escolha uma das seguintes opções:");

            int choice = Choice();

            if(choice == 1)
            {
                funcoes.add();
            }

            if(choice == 2)
            {
                funcoes.remove();
            }

            if(choice == 3)
            {
                funcoes.show_employees();
            }
            
            if(choice == 4)
            {
                funcoes.time_card();
            }

            if(choice == 5)
            {
                funcoes.sale();
            }

            if(choice == 6)
            {
                funcoes.tax();
            }
            
            if(choice == 7)
            {
                funcoes.change_data();
            }

            if(choice == 8)
            {
                funcoes.create_schedule();
            }

            if(choice == 9)
            {
                funcoes.pay_roll();
            }

            if(choice == 10)
            {
                funcoes.undo();
            }

            if(choice == 11)
            {
                funcoes.redo();
            }

            if(choice == 12) //o codigo finaliza
            {
                System.out.println("Finalizando o sistema");
                System.out.println("...");
                break;
            }
        }

    }

    private int Choice()
    {
        System.out.println("OBS: Digite o código da sua ação\n");
        System.out.println("Adicionar novo funcionário [1]");
        System.out.println("Remover Funcionarios       [2]");
        System.out.println("Listar Funcionarios        [3]");
        System.out.println("Lançar Cartão de ponto     [4]");
        System.out.println("Lançar Venda               [5]");
        System.out.println("Lançar Taxa de Serviço     [6]");
        System.out.println("Alterar Dados do empregado [7]");
        System.out.println("Criar agenda de pagamento  [8]");
        System.out.println("Folha de pagamento         [9]");
        System.out.println("Undo                       [10]");
        System.out.println("Redo                       [11]");
        System.out.println("Sair do sistema            [12]");

        while(true)
        {
            int code = Validar.int_valido();

            if(code >= 1 && code <= 12)
            {
                return code;
            }

            else
            {
                System.out.print("Opcão invalida, digite novamente: ");
            }
        }
    }
}