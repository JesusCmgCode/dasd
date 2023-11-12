package SistemaPaciente;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class SistemaHospital {

    public List<Paciente> listaPacientes = new ArrayList<>();
    public List<Medico> listaMedicos = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    public void registrarPaciente(Paciente paciente) {
        listaPacientes.add(paciente);

        if (!listaMedicos.isEmpty()) {
            // ASIGNAMOS MEDICO AUTOMATICAMENTE AL REGISTRAR PACIENTE
            int posicionMedico = listaPacientes.size() - 1;
            Medico medicoAsignado = listaMedicos.get(posicionMedico);
            paciente.setMedicoAsignado(medicoAsignado);
        }
    }

    public void eliminarPaciente(int posicion) {
        if (posicion >= 0 && posicion < listaPacientes.size()) {
            listaPacientes.remove(posicion);
        }
    }

    public void modificarPaciente(int posicion, Paciente pacienteModificado) {
        if (posicion >= 0 && posicion < listaPacientes.size()) {
            listaPacientes.set(posicion, pacienteModificado);
        }
    }

    public double pesoMasRepetido() {

        Map<Double, Integer> pesoFrecuencia = new HashMap<>();

        for (Paciente paciente : listaPacientes) {
            double peso = paciente.getPeso();
            pesoFrecuencia.put(peso, pesoFrecuencia.getOrDefault(peso, 0) + 1);
        }

        double pesoMasRepetido = 0.0;
        int frecuenciaMaxima = 0;

        for (Map.Entry<Double, Integer> entry : pesoFrecuencia.entrySet()) {
            if (entry.getValue() > frecuenciaMaxima) {
                frecuenciaMaxima = entry.getValue();
                pesoMasRepetido = entry.getKey();
            }
        }

        return pesoMasRepetido;
    }

    public int cantidadPacientesConPesoRepetido() {

        double pesoMasRepetido = pesoMasRepetido();
        int cantidad = 0;

        for (Paciente paciente : listaPacientes) {
            if (paciente.getPeso() == pesoMasRepetido) {
                cantidad++;
            }
        }

        return cantidad;
    }

    public void mostrarPesoMayorYMenor() {

        if (listaPacientes.isEmpty()) {
            System.out.println("No hay pacientes registrados.");
            return;
        }

        double pesoMayor = listaPacientes.get(0).getPeso();
        double pesoMenor = listaPacientes.get(0).getPeso();

        for (Paciente paciente : listaPacientes) {
            double peso = paciente.getPeso();
            if (peso > pesoMayor) {
                pesoMayor = peso;
            }
            if (peso < pesoMenor) {
                pesoMenor = peso;
            }
        }

        System.out.println("Peso mayor: " + pesoMayor);
        System.out.println("Peso menor: " + pesoMenor);
    }

    public void mostrarCantidadPersonasPorRango() {

        if (listaPacientes.isEmpty()) {
            System.out.println("No hay pacientes registrados.");
            return;
        }

        double pesoMaximo = listaPacientes.get(0).getPeso();
        double pesoMinimo = listaPacientes.get(0).getPeso();

        for (Paciente paciente : listaPacientes) {
            double peso = paciente.getPeso();
            if (peso > pesoMaximo) {
                pesoMaximo = peso;
            }
            if (peso < pesoMinimo) {
                pesoMinimo = peso;
            }
        }

        double rango = (pesoMaximo - pesoMinimo) / 4.0;

        int rango1 = 0, rango2 = 0, rango3 = 0, rango4 = 0;

        for (Paciente paciente : listaPacientes) {
            double peso = paciente.getPeso();
            if (peso >= pesoMinimo && peso < pesoMinimo + rango) {
                rango1++;
            } else if (peso >= pesoMinimo + rango && peso < pesoMinimo + 2 * rango) {
                rango2++;
            } else if (peso >= pesoMinimo + 2 * rango && peso < pesoMinimo + 3 * rango) {
                rango3++;
            } else {
                rango4++;
            }
        }

        System.out.println("Rango 1 (" + pesoMinimo + " - " + (pesoMinimo + rango) + "): " + rango1 + " personas");
        System.out.println("Rango 2 (" + (pesoMinimo + rango) + " - " + (pesoMinimo + 2 * rango) + "): " + rango2 + " personas");
        System.out.println("Rango 3 (" + (pesoMinimo + 2 * rango) + " - " + (pesoMinimo + 3 * rango) + "): " + rango3 + " personas");
        System.out.println("Rango 4 (" + (pesoMinimo + 3 * rango) + " - " + pesoMaximo + "): " + rango4 + " personas");
    }

    public void mostrarPacientesOrdenadosPorNombre() {
        if (listaPacientes.isEmpty()) {
            System.out.println("No hay pacientes registrados.");
            return;
        }

        Collections.sort(listaPacientes, Comparator.comparing(Paciente::getNombre));

        System.out.println("Lista de pacientes ordenados por nombre:");
        for (Paciente paciente : listaPacientes) {
            System.out.println("Nombre: " + paciente.getNombre() + ", DNI: " + paciente.getDNI());
        }
    }

    public Medico obtenerMedicoDePaciente(Paciente paciente) {
        return paciente.getMedicoAsignado();
    }

    public List<Medico> buscarDoctoresPorEspecialidad(String especialidad) {
        List<Medico> doctoresEncontrados = new ArrayList<>();
        for (Paciente paciente : listaPacientes) {
            Medico medico = paciente.getMedicoAsignado();
            if (medico != null && medico.getEspecialidad().equalsIgnoreCase(especialidad)) {
                if (!doctoresEncontrados.contains(medico)) {
                    doctoresEncontrados.add(medico);
                }
            }
        }
        return doctoresEncontrados;
    }

    public void mostrarMedicoDePaciente() {
        if (!listaPacientes.isEmpty()) {
            System.out.println("Lista de pacientes:");
            for (int i = 0; i < listaPacientes.size(); i++) {
                System.out.println(i + ". " + listaPacientes.get(i).getNombre());
            }

            System.out.println("Ingrese el numero del paciente del cual desea conocer al medico:");
            int posicionPaciente = scanner.nextInt();
            scanner.nextLine();

            Paciente pacienteSeleccionado = listaPacientes.get(posicionPaciente);
            Medico medicoAtendio = pacienteSeleccionado.getMedicoAsignado();

            if (medicoAtendio != null) {
                System.out.println("El paciente fue atendido por el médico:");
                System.out.println("Numero de colegiatura: " + medicoAtendio.getNumeroColegiatura());
                System.out.println("Nombre: " + medicoAtendio.getNombre());
                System.out.println("Especialidad: " + medicoAtendio.getEspecialidad());
            } else {
                System.out.println("No se encontró información sobre el médico del paciente.");
            }
        } else {
            System.out.println("No hay pacientes registrados.");
        }
    }

    private void createMedico(StringTokenizer st) {
        int numeroColegiatura = Integer.parseInt(st.nextToken().trim());
        String nombre = st.nextToken().trim();
        String especialidad = st.nextToken().trim();
        Medico medico = new Medico(numeroColegiatura, nombre, especialidad);
        listaMedicos.add(medico);
    }

    public void cargarDoctoresDesdeArchivo(String nombreArchivo) {
        try {
            InputStream inputStream = getClass().getResourceAsStream("/SistemaPaciente/Doctores.txt");

            if (inputStream != null) {
                // Procesa el archivo
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                String linea;
                while ((linea = br.readLine()) != null) {
                    StringTokenizer st = new StringTokenizer(linea, ",");
                    createMedico(st);
                }
                br.close();
            } else {
                System.out.println("No se pudo encontrar el archivo Doctores.txt en el directorio de recursos.");
            }
        } catch (Exception e) {
            System.out.println("Error al cargar el archivo Doctores.txt: " + e.getMessage());
        }
    }
}
