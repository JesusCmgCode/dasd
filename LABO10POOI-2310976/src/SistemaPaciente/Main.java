package SistemaPaciente;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        SistemaHospital sistemaHospital = new SistemaHospital();
        sistemaHospital.cargarDoctoresDesdeArchivo("Doctores.txt");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Seleccione una operacion:");
            System.out.println("1. Registrar paciente");
            System.out.println("2. Eliminar paciente");
            System.out.println("3. Modificar paciente");
            System.out.println("4. Mostrar peso mas repetido");
            System.out.println("5. Mostrar cantidad de pacientes con peso repetido");
            System.out.println("6. Mostrar peso mayor y menor");
            System.out.println("7. Mostrar cantidad de personas por rango de peso");
            System.out.println("8. Mostrar pacientes ordenados por apellidos");
            System.out.println("9. Mostrar medico de un paciente");
            System.out.println("10. Buscar doctores por especialidad");
            System.out.println("0. Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:

                    System.out.println("Ingresa DNI del paciente:");
                    String dni = scanner.nextLine();
                    System.out.println("Nombre del paciente:");
                    String nombrePaciente = scanner.nextLine();
                    System.out.println("Direccion del paciente:");
                    String direccion = scanner.nextLine();
                    System.out.println("Cuando Pesa el paciente:");
                    double peso = scanner.nextDouble();
                    System.out.println("Temperatura del paciente:");
                    double temperatura = scanner.nextDouble();

                    Paciente nuevoPaciente = new Paciente(dni, nombrePaciente, direccion, peso, temperatura);
                    sistemaHospital.registrarPaciente(nuevoPaciente);

                    System.out.println("El paciente se Registro ");
                    break;

                case 2:

                    if (!sistemaHospital.listaPacientes.isEmpty()) {
                        System.out.println("Lista de pacientes:");
                        for (int i = 0; i < sistemaHospital.listaPacientes.size(); i++) {
                            System.out.println(i + ". " + sistemaHospital.listaPacientes.get(i).getNombre());
                        }

                        System.out.println("Ingrese el numero del paciente que desea eliminar:");
                        int posicionEliminar = scanner.nextInt();
                        scanner.nextLine();
                        sistemaHospital.eliminarPaciente(posicionEliminar);
                        System.out.println("Paciente eliminado");
                    } else {
                        System.out.println("No hay pacientes registrados");
                    }
                    break;

                case 3:

                    if (!sistemaHospital.listaPacientes.isEmpty()) {
                        System.out.println("Lista de pacientes:");
                        for (int i = 0; i < sistemaHospital.listaPacientes.size(); i++) {
                            System.out.println(i + ". " + sistemaHospital.listaPacientes.get(i).getNombre());
                        }

                        System.out.println("Ingrese el numero del paciente que desea modificar:");
                        int posicionModificar = scanner.nextInt();
                        scanner.nextLine();

                        System.out.println("Ingrese nuevos datos del paciente:");

                        System.out.println("Ingrese nuevo DNI del paciente:");
                        dni = scanner.nextLine();
                        System.out.println("Ingrese nuevo nombre del paciente:");
                        nombrePaciente = scanner.nextLine();
                        System.out.println("Ingrese nueva direccion del paciente:");
                        direccion = scanner.nextLine();
                        System.out.println("Ingrese nuevo peso del paciente:");
                        peso = scanner.nextDouble();
                        System.out.println("Ingrese nueva temperatura del paciente:");
                        temperatura = scanner.nextDouble();

                        Paciente pacienteModificado = new Paciente(dni, nombrePaciente, direccion, peso, temperatura);
                        sistemaHospital.modificarPaciente(posicionModificar, pacienteModificado);

                        System.out.println("El paciente se modifico");
                    } else {
                        System.out.println("No hay pacientes registrados");
                    }
                    break;

                case 4:

                    double pesoMasRepetido = sistemaHospital.pesoMasRepetido();
                    System.out.println("Peso mas repetido: " + pesoMasRepetido);
                    break;

                case 5:

                    int cantidadPacientesConPesoRepetido = sistemaHospital.cantidadPacientesConPesoRepetido();
                    System.out.println("Cantidad de pacientes con peso repetido: " + cantidadPacientesConPesoRepetido);
                    break;

                case 6:

                    sistemaHospital.mostrarPesoMayorYMenor();
                    break;

                case 7:

                    sistemaHospital.mostrarCantidadPersonasPorRango();
                    break;

                case 8:

                    sistemaHospital.mostrarPacientesOrdenadosPorNombre();
                    break;

                case 9:
                    if (!sistemaHospital.listaPacientes.isEmpty()) {
                        System.out.println("Lista de pacientes:");
                        for (int i = 0; i < sistemaHospital.listaPacientes.size(); i++) {
                            System.out.println(i + ". " + sistemaHospital.listaPacientes.get(i).getNombre());
                        }

                        System.out.println("Ingrese el numero del paciente del cual desea conocer al medico:");
                        int posicionPaciente = scanner.nextInt();
                        scanner.nextLine();

                        Paciente pacienteSeleccionado = sistemaHospital.listaPacientes.get(posicionPaciente);

                        if (pacienteSeleccionado.getMedicoAsignado() != null) {
                            // Se encontró información del médico directamente desde el paciente
                            Medico medicoAtendio = pacienteSeleccionado.getMedicoAsignado();
                            System.out.println("El paciente fue atendido por el medico:");
                            System.out.println("Numero de colegiatura: " + medicoAtendio.getNumeroColegiatura());
                            System.out.println("Nombre: " + medicoAtendio.getNombre());
                            System.out.println("Especialidad: " + medicoAtendio.getEspecialidad());
                        } else {
                            // Intentar buscar información del médico en la lista de doctores
                            System.out.println("No se encontró información sobre el medico del paciente.");
                            System.out.println("Intentando buscar información en la lista de doctores...");

                            for (Medico medico : sistemaHospital.listaMedicos) {
                                if (medico.getNumeroColegiatura() == pacienteSeleccionado.getMedicoAsignado().getNumeroColegiatura()) {
                                    System.out.println("El paciente fue atendido por el medico:");
                                    System.out.println("Numero de colegiatura: " + medico.getNumeroColegiatura());
                                    System.out.println("Nombre: " + medico.getNombre());
                                    System.out.println("Especialidad: " + medico.getEspecialidad());
                                    break;
                                }
                            }
                        }
                    } else {
                        System.out.println("No hay pacientes registrados.");
                    }
                    break;

                case 10:

                    System.out.println("Ingrese la especialidad que desea buscar:");
                    String especialidadBuscada = scanner.nextLine();
                    List<Medico> doctoresEncontrados = new ArrayList<>();

                    for (Medico medico : sistemaHospital.listaMedicos) {
                        if (medico.getEspecialidad().equalsIgnoreCase(especialidadBuscada)) {
                            doctoresEncontrados.add(medico);
                        }
                    }

                    if (!doctoresEncontrados.isEmpty()) {
                        System.out.println("Doctores encontrados con la especialidad '" + especialidadBuscada + "':");
                        for (Medico medico : doctoresEncontrados) {
                            System.out.println("Numero de colegiatura: " + medico.getNumeroColegiatura());
                            System.out.println("Nombre: " + medico.getNombre());
                            System.out.println("Especialidad: " + medico.getEspecialidad());
                            System.out.println("------");
                        }
                    } else {
                        System.out.println("No se encontraron doctores con la especialidad '" + especialidadBuscada + "'.");
                    }
                    break;

                case 0:

                    System.out.println("Saliendo del programa");
                    System.exit(0);

                default:
                    System.out.println("Opcion invalida, Seleccione otra");
            }
        }
    }
}
