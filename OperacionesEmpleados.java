import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class OperacionesEmpleados {

    private final List<Empleado> empleados;
    private final Scanner scanner = new Scanner(System.in);

    public OperacionesEmpleados(){
        empleados = new ArrayList<Empleado>(){
            {
                add(new Empleado(1234, "Sebas", "Alarconn", 1000.0));
                add(new Empleado(2345, "Fulano", "Perez", 2000.0));
                add(new Empleado(2346, "Fulano2", "Perez2", 700001.0));
                add(new Empleado(2326, "Fulano3", "Perez6", 700002.0));
                add(new Empleado(2846, "Fulano5", "alfredo", 700005.0));
                
            }
        };
    }

    public void agregar(){
        System.out.println("Ingrese la cedula");
        int cedula = obtenerCedula();
        existeCedula(cedula);
        System.out.println("Ingrese el nombre");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese el apellido");
        String apellido = scanner.nextLine();
        System.out.println("Ingrese el salario");
        double salario = obtenerSalario();

        empleados.add(
                new Empleado(cedula, nombre, apellido, salario)
        );
    }

    private int obtenerCedula(){

        try{
            int cedula = scanner.nextInt();
            scanner.nextLine();
            return cedula;
        } catch (InputMismatchException ex) {
            scanner.nextLine();
            throw new EmpleadoException("Cedula no valida");
        }
    }

    private void existeCedula(int cedula) {

        for(Empleado empleado : empleados){
            if(empleado.getId() == cedula){
                throw new EmpleadoException("Cedula " + cedula + " ya existe");
            }
        }
    }

    private boolean noExisteCedula(int cedula){

        try{
            existeCedula(cedula);
            return true;
        } catch (EmpleadoException ex){
            return false;
        }
    }

    private double obtenerSalario() {

        try{
            double salario = scanner.nextDouble();
            scanner.nextLine();
            return salario;
        } catch (InputMismatchException ex) {
            scanner.nextLine();
            throw new EmpleadoException("Salario con formato no valido");
        }
    }

    public void eliminar(){

        System.out.println("Ingrese la cedula que quiere eliminar");
        int cedula = obtenerCedula();
        if(!noExisteCedula(cedula)) {
            for (Empleado empleado : empleados) {
                if (empleado.getId() == cedula) {
                    System.out.println("Se ha eliminado al empleado " + empleado);
                    empleados.remove(empleado);
                }
            }
        } else {
            throw new EmpleadoException("La cedula " + cedula + " no existe");
        }
    }

    public void actualizar(){

        System.out.println("Ingrese la cedula que quiere actualizar");
        int cedula = obtenerCedula();
        if(!noExisteCedula(cedula)){
            int opcion = 0;
            do {
                mostrarMenuActualizar();
                try {
                    opcion = scanner.nextInt();
                } catch (InputMismatchException ex) {
                    opcion = -1;
                }
                scanner.nextLine();
                actualizarEmpleado(cedula, opcion);
            } while(opcion != 4);
        } else {
            throw new EmpleadoException("La cedula " + cedula + " no existe");
        }
    }

    private void mostrarMenuActualizar(){
        System.out.println("Elija una opcion:");
        System.out.println("1. Nombre");
        System.out.println("2. Apellido");
        System.out.println("3. Salario");
        System.out.println("4. Salir");
    }

    private void actualizarEmpleado(int cedula, int opcion){

        Empleado empleadoViejo = obtenerEmpleado(cedula);
        Empleado nuevoEmpleado = null;
        if(null != empleadoViejo){
            elegirOpcionDeActualizacion(opcion, empleadoViejo, nuevoEmpleado);
        } else {
            throw new EmpleadoException("La cedula " + cedula + " no existe");
        }
    }

    private void elegirOpcionDeActualizacion(int opcion, Empleado empleadoViejo, Empleado nuevoEmpleado){

        switch (opcion){
            case 1:
                System.out.println("Ingrese el nuevo nombre");
                String nuevoNombre = scanner.nextLine();
                nuevoEmpleado = new Empleado(empleadoViejo.getId(), nuevoNombre, empleadoViejo.getApellido(), empleadoViejo.getSalario());
                break;
            case 2:
                System.out.println("Ingrese el nuevo apellido");
                String nuevoApellido = scanner.nextLine();
                nuevoEmpleado = new Empleado(empleadoViejo.getId(), empleadoViejo.getNombre(), nuevoApellido, empleadoViejo.getSalario());
                break;
            case 3:
                System.out.println("Ingrese el nuevo salario");
                double nuevoSalario = obtenerSalario();
                nuevoEmpleado = new Empleado(empleadoViejo.getId(), empleadoViejo.getNombre(), empleadoViejo.getApellido(), nuevoSalario);
                break;
            case 4:
                break;
            default:
                System.out.println("opcion no valida");
        }

        if(opcion >= 1 && opcion <= 3){
            empleados.remove(empleadoViejo);
            empleados.add(nuevoEmpleado);
        }
    }

    public void mostrar(){
        for(Empleado empleado : empleados){
            System.out.println(empleado);
        }
    }

    private Empleado obtenerEmpleado(int cedula){

        for(Empleado empleado : empleados){
            if(cedula == empleado.getId()){
                return empleado;
            }
        }

        return null;
    } // <>
    public void SalarioMax() {
    	double salmax;
    	double salmin;
    	String nombremin ="";
    	String nombremax ="";
    	salmax=salmin=empleados.get(0).getSalario();
    	nombremax=nombremin=empleados.get(0).getNombre();
    	for(int i=0; i<empleados.size(); i++) {
    		
    		if(empleados.get(i).getSalario()>salmax) {
    			salmax=empleados.get(i).getSalario();
    			nombremax=empleados.get(i).getNombre();
    		}
    		
    	}
    	
    	
for(int i=0; i<empleados.size(); i++) {
    		
    		if(empleados.get(i).getSalario()<salmin) {
    			salmin=empleados.get(i).getSalario();
    			nombremin=empleados.get(i).getNombre();
    		}
    		
    	}

        System.out.println("el empleado: " + nombremin + " tiene el salario minimo de :" +salmin);
    	System.out.println("el empleado: " + nombremax + " tiene el salario maximo de :" +salmax);

    }
    public void mostrarOrden(){
    	
    	String[] nombres;
    	nombres= new String[empleados.size()];
for(int i=0; i<empleados.size(); i++) {
    		
    		nombres[i]=empleados.get(i).getNombre();
    			
    	}
List<String> L1 = Arrays.asList(nombres);
Collections.sort(L1);
System.out.println(L1);


        }
    
public void SumaMayor(){
	
	double salarios=0;
	
ArrayList<Empleado> salariover= (ArrayList<Empleado>) empleados.stream().filter((Empleado Empleado)->{return Empleado.getSalario()>700000;}).collect(Collectors.toList());
for(int i=0; i<salariover.size(); i++) {
	
salarios= salarios+salariover.get(i).getSalario();
			
	}
System.out.println(salariover);
System.out.println("la suma de los salarios de mas de 700 000 es de :" + salarios);

    }
public void ApellidosA(){
	String[] nombres;
	String letra;
	char caracter;
	int contador=0;
	nombres= new String[empleados.size()];
for(int i=0; i<empleados.size(); i++) {
		
		nombres[i]=empleados.get(i).getApellido();
			
	}
	
	for(int i=0; i<empleados.size(); i++) {
		letra = nombres[i];
		caracter= letra.charAt(0);
		if(caracter=='A' || caracter=='a')
		{
			contador++;
		}
		
		
			
	}
	


System.out.println("Hay " + contador + " empleados con apellidos iniciados en A" );

    }
public void cincoPrimeros(){
	
	double[] salarios;
	salarios= new double[empleados.size()];
for(int i=0; i<empleados.size(); i++) {
		
		salarios[i]=empleados.get(i).getSalario();
			
	}

Arrays.sort(salarios);
for(int i=0; i<empleados.size()/2; i++) {
	double temp=salarios[i];
	salarios[i]=salarios[salarios.length -i -1];
	salarios[salarios.length -i -1]=temp;
		
}
for(int i=0; i<5; i++) {
	System.out.println(salarios[i]);
		
}






    }
}

    

