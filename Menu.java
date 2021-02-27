import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    private final Scanner scanner = new Scanner(System.in);
    private final OperacionesEmpleados operacionesEmpleados = new OperacionesEmpleados();

    private void mostrarMenu(){

        System.out.println("\nPor favor elija una opcion");
        System.out.println("1. Agregar empleado");
        System.out.println("2. Eliminar empleado");
        System.out.println("3. Actualizar empleado");
        System.out.println("4. Mostrar empleados");
        System.out.println("5. Mostrar mayor y menor salario ");
        System.out.println("6. Mostrar lista ordenada por nombre ");
        System.out.println("7. Mostrar suma de sueldos de mas de 700 000");
        System.out.println("8. Apellidos iniciados en A");
        System.out.println("9. Top 5 sueldos de empleados : ");
       
        System.out.println("0. Salir");
    }

    private void eligirOpcion(int opcion){

        switch(opcion){
        case 0:
        	System.exit(0);
        	break;
            case 1:
                System.out.println("Agregando");
                operacionesEmpleados.agregar();
                break;
            case 2:
                System.out.println("Eliminando");
                operacionesEmpleados.eliminar();
                break;
            case 3:
                System.out.println("Actualizando");
                operacionesEmpleados.actualizar();
                break;
            case 4:
                System.out.println("Mostrando");
                operacionesEmpleados.mostrar();
                break;
            case 5:
                System.out.println("mayor y menor salario: ");
                operacionesEmpleados.SalarioMax();
                break;
            case 6:
                System.out.println("ordenando...");
                operacionesEmpleados.mostrarOrden();
                break;
            case 7:
                System.out.println("suma de los salarios de mas de 700 000");
                operacionesEmpleados.SumaMayor();
                break;
            case 8:
                
                operacionesEmpleados.ApellidosA();
                break;
                
 case 9:
                
                operacionesEmpleados.cincoPrimeros();
                break;
                
            
            default:
                otrasOpciones(opcion);
        }
    }

    public void otrasOpciones(int opcion){

        switch (opcion){
            case 5:
            case 11:
                System.out.println("Adios");
                break;
            default:
                System.out.println("La opcion no es valida");
        }
    }

    public void repetirMenu() {

        int opcion = 0;
        do {
            mostrarMenu();
            opcion = obtenerOpcion();
            try {
                eligirOpcion(opcion);
            }catch (EmpleadoException ex){
                System.out.println(ex.getErrorMessage());
            }
        }while(opcion != 10);
    }

    private int obtenerOpcion(){

        try {
            return scanner.nextInt();
        } catch(InputMismatchException ex) {
            scanner.nextLine();
            return -1;
        }
    }
}
