package SistemaPaciente;

public class Paciente {
    private String DNI;
    private String nombre;
    private String direccion;
    private double peso;
    private double temperatura;
    private Medico medicoAsignado;

  
    public Paciente(String DNI, String nombre, String direccion, double peso, double temperatura) {
        this.DNI = DNI;
        this.nombre = nombre;
        this.direccion = direccion;
        this.peso = peso;
        this.temperatura = temperatura;
    }


    public String getDNI() {
        return DNI;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public double getPeso() {
        return peso;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public Medico getMedicoAsignado() {
        return medicoAsignado;
    }

    public void setMedicoAsignado(Medico medico) {
        this.medicoAsignado = medico;
    }
}
