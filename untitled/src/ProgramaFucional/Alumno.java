package ProgramaFucional;

public class Alumno {
    // Atributos
    private String nombre;
    private double nota;
    private String curso;

    // Constructor
    public Alumno(String nombre, double nota, String curso) {
        this.nombre = nombre;
        this.nota = nota;
        this.curso = curso;
    }

    // Getters (necesarios para usar con Streams)
    public String getNombre() {
        return nombre;
    }

    public double getNota() {
        return nota;
    }

    public String getCurso() {
        return curso;
    }

    // Método toString() para imprimir el objeto de forma legible
    @Override
    public String toString() {
        return nombre + " (Nota: " + nota + ", Curso: " + curso + ")";
    }
}