package ProgramaFucional;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // --- CASO 1: ALUMNOS ---
        List<Alumno> alumnos = Arrays.asList(
                new Alumno("Lucía", 8.5, "Matemática"),
                new Alumno("Juan", 6.0, "Historia"),
                new Alumno("Ana", 9.0, "Matemática"),
                new Alumno("Pedro", 7.5, "Física")
        );
        System.out.println("=== ALUMNOS ===");
        // 1. Nombres de aprobados en mayúsculas y ordenados
        List<String> nombresAprobados = alumnos.stream()
                .filter(a -> a.getNota() >= 7)
                .map(a -> a.getNombre().toUpperCase())
                .sorted()
                .collect(Collectors.toList());
        System.out.println("Aprobados: " + nombresAprobados);

        // 2. Promedio general
        double promedioAlumnos = alumnos.stream()
                .mapToDouble(Alumno::getNota)
                .average()
                .orElse(0.0);
        System.out.println("Promedio: " + promedioAlumnos);

        // 3. Agrupar por curso
        Map<String, List<Alumno>> alumnosPorCurso = alumnos.stream()
                .collect(Collectors.groupingBy(Alumno::getCurso));
        System.out.println("Alumnos por curso: " + alumnosPorCurso);

        // 4. Los 3 mejores promedios
        List<Double> mejoresPromedios = alumnos.stream()
                .sorted(Comparator.comparingDouble(Alumno::getNota).reversed())
                .limit(3)
                .map(Alumno::getNota)
                .collect(Collectors.toList());
        System.out.println("Top 3 notas: " + mejoresPromedios);

        // --- CASO 2: PRODUCTOS ---
        List<Producto> productos = Arrays.asList(
                new Producto("Notebook", "Tecnología", 1200.0, 10),
                new Producto("Silla", "Muebles", 150.0, 20),
                new Producto("Monitor", "Tecnología", 300.0, 5),
                new Producto("Mesa", "Muebles", 250.0, 8)
        );
        System.out.println("\n=== PRODUCTOS ===");
        // 1. Productos con precio > 100, ordenados por precio descendente
        List<Producto> productosCaros = productos.stream()
                .filter(p -> p.getPrecio() > 100)
                .sorted(Comparator.comparingDouble(Producto::getPrecio).reversed())
                .collect(Collectors.toList());
        System.out.println("Productos caros: " + productosCaros);

        // 2. Stock total por categoría
        Map<String, Integer> stockPorCategoria = productos.stream()
                .collect(Collectors.groupingBy(
                        Producto::getCategoria,
                        Collectors.summingInt(Producto::getStock)
                ));
        System.out.println("Stock por categoría: " + stockPorCategoria);

        // 3. String con productos (nombre;precio)
        String productosString = productos.stream()
                .map(p -> p.getNombre() + ";" + p.getPrecio())
                .collect(Collectors.joining(" | "));
        System.out.println("Productos (formato String): " + productosString);

        // 4. Precio promedio por categoría
        Map<String, Double> promedioPorCategoria = productos.stream()
                .collect(Collectors.groupingBy(
                        Producto::getCategoria,
                        Collectors.averagingDouble(Producto::getPrecio)
                ));
        System.out.println("Promedio por categoría: " + promedioPorCategoria);

        // --- CASO 3: LIBROS ---
        List<Libro> libros = Arrays.asList(
                new Libro("El Quijote", "Cervantes", 1000, 25.5),
                new Libro("Cien años de soledad", "García Márquez", 400, 30.0),
                new Libro("Java 8 en acción", "Urma", 500, 45.0),
                new Libro("Clean Code", "Martin", 350, 40.0)
        );
        System.out.println("\n=== LIBROS ===");
        // 1. Títulos con más de 300 páginas, ordenados alfabéticamente
        List<String> titulosLargos = libros.stream()
                .filter(l -> l.getPaginas() > 300)
                .map(Libro::getTitulo)
                .sorted()
                .collect(Collectors.toList());
        System.out.println("Libros largos: " + titulosLargos);

        // 2. Promedio de páginas
        double promedioPaginas = libros.stream()
                .mapToInt(Libro::getPaginas)
                .average()
                .orElse(0.0);
        System.out.println("Promedio de páginas: " + promedioPaginas);

        // 3. Libros por autor (contar)
        Map<String, Long> librosPorAutor = libros.stream()
                .collect(Collectors.groupingBy(
                        Libro::getAutor,
                        Collectors.counting()
                ));
        System.out.println("Libros por autor: " + librosPorAutor);

        // 4. Libro más caro
        Libro libroMasCaro = libros.stream()
                .max(Comparator.comparingDouble(Libro::getPrecio))
                .orElse(null);
        System.out.println("Libro más caro: " + libroMasCaro);

        // --- CASO 4: EMPLEADOS ---
        List<Empleado> empleados = Arrays.asList(
                new Empleado("Carlos", "Ventas", 2500.0, 30),
                new Empleado("María", "RRHH", 1800.0, 25),
                new Empleado("Luis", "Tecnología", 3200.0, 35),
                new Empleado("Ana", "Ventas", 2100.0, 28)
        );
        System.out.println("\n=== EMPLEADOS ===");
        // 1. Empleados con salario > 2000, ordenados por salario descendente
        List<Empleado> empleadosBienPagos = empleados.stream()
                .filter(e -> e.getSalario() > 2000)
                .sorted(Comparator.comparingDouble(Empleado::getSalario).reversed())
                .collect(Collectors.toList());
        System.out.println("Empleados bien pagos: " + empleadosBienPagos);

        // 2. Salario promedio
        double salarioPromedio = empleados.stream()
                .mapToDouble(Empleado::getSalario)
                .average()
                .orElse(0.0);
        System.out.println("Salario promedio: " + salarioPromedio);

        // 3. Salarios por departamento (suma)
        Map<String, Double> salariosPorDepartamento = empleados.stream()
                .collect(Collectors.groupingBy(
                        Empleado::getDepartamento,
                        Collectors.summingDouble(Empleado::getSalario)
                ));
        System.out.println("Salarios por departamento: " + salariosPorDepartamento);

        // 4. Nombres de los 2 empleados más jóvenes
        List<String> nombresJovenes = empleados.stream()
                .sorted(Comparator.comparingInt(Empleado::getEdad))
                .limit(2)
                .map(Empleado::getNombre)
                .collect(Collectors.toList());
        System.out.println("2 empleados más jóvenes: " + nombresJovenes);
    }
}