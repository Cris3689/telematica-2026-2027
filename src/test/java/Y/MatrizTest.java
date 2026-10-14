package Y;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import org.junit.Test;


/**
 * Pruebas de los constructores de Matriz.
 */
public class MatrizTest {

    /*
     * Comprobamos el caso más sencillo:
     * el constructor recibe una matriz rectangular.
     */
    @Test
    public void constructorMatrizRectangular() {

        double[][] datos = {
            { 1.0, 2.0, 3.0 },
            { 4.0, 5.0, 6.0 }
        };

        Matriz matriz = new Matriz(datos);

        assertArrayEquals(new int[] { 2, 3 }, matriz.getDimension());
        assertEquals(1.0, matriz.getElemento(0, 0), 0.0);
        assertEquals(6.0, matriz.getElemento(1, 2), 0.0);
    }

    @Test
    public void construyeMatrizDeUnaCelda() {
        Matriz matriz = new Matriz(new double[][] { { 7.5 } });

        assertArrayEquals(new int[] { 1, 1 }, matriz.getDimension());
        assertEquals(7.5, matriz.getElemento(0, 0), 0.0);
    }

    @Test
    public void construyeMatrizDeUnaFila() {
        Matriz matriz = new Matriz(new double[][] { { 2.0, 4.0, 6.0 } });

        assertArrayEquals(new int[] { 1, 3 }, matriz.getDimension());
        assertEquals(2.0, matriz.getElemento(0, 0), 0.0);
        assertEquals(6.0, matriz.getElemento(0, 2), 0.0);
    }

    @Test
    public void construyeMatrizDeUnaColumna() {
        Matriz matriz = new Matriz(new double[][] { { 2.0 }, { 4.0 }, { 6.0 } });

        assertArrayEquals(new int[] { 3, 1 }, matriz.getDimension());
        assertEquals(2.0, matriz.getElemento(0, 0), 0.0);
        assertEquals(6.0, matriz.getElemento(2, 0), 0.0);
    }

    /*
     * Si las filas no tienen la misma longitud,
     * la matriz toma como número de columnas la longitud
     * de la fila más larga y completa con ceros.
     */
    @Test
    public void constructorRellenaConCeros() {

        double[][] datos = {
            { 1.0 },
            { 4.0, 5.0, 6.0 },
            { },
            { 7.0, 8.0 }
        };

        Matriz matriz = new Matriz(datos);

        assertArrayEquals(new int[] { 4, 3 }, matriz.getDimension());
        assertEquals(1.0, matriz.getElemento(0, 0), 0.0);
        assertEquals(0.0, matriz.getElemento(0, 1), 0.0);
        assertEquals(4.0, matriz.getElemento(1, 0), 0.0);
        assertEquals(5.0, matriz.getElemento(1, 1), 0.0);
        assertEquals(6.0, matriz.getElemento(1, 2), 0.0);
        assertEquals(0.0, matriz.getElemento(2, 0), 0.0);
        assertEquals(0.0, matriz.getElemento(2, 2), 0.0);
        assertEquals(7.0, matriz.getElemento(3, 0), 0.0);
        assertEquals(8.0, matriz.getElemento(3, 1), 0.0);
        assertEquals(0.0, matriz.getElemento(3, 2), 0.0);
    }

    /*
     * El constructor debe copiar los datos recibidos.
     * Modificar posteriormente el array original no debe
     * modificar la matriz.
     */
    @Test
    public void constructorHaceUnaCopiaDeLosDatos() {

        double[][] datos = {
            { 1.0, 2.0 },
            { 3.0, 4.0 }
        };

        Matriz matriz = new Matriz(datos);
        datos[0][0] = 99.0;
        datos[1] = new double[] { 88.0 };

        assertEquals(1.0, matriz.getElemento(0, 0), 0.0);
        assertEquals(4.0, matriz.getElemento(1, 1), 0.0);
    }

    /* Comprobamos que el constructor rechaza un array null. */
    @Test
    public void constructorMatrizNull() {
        assertEquals("La matriz debe tener al menos una fila",
            assertThrows(IllegalArgumentException.class, () -> new Matriz(null)).getMessage());
    }  
    
    /* Comprobamos que el constructor rechaza un array vacío. */
    @Test
    public void constructorMatrizVacia() {
        assertEquals("La matriz debe tener al menos una fila",
            assertThrows(IllegalArgumentException.class,
                () -> new Matriz(new double[0][])).getMessage());
    }

    /* Comprobamos que el constructor rechaza filas sin columnas. */
    @Test
    public void constructorMatrizConFilasVacias() {
        double[][] datos = {
            { },
            { }
        };

        assertEquals("La matriz debe tener al menos una columna",
            assertThrows(IllegalArgumentException.class,
                () -> new Matriz(datos)).getMessage());
    }

    /* Comprobamos que el constructor rechaza filas null. */
    @Test
    public void constructorMatrizConFilasNull() {
        double[][] datos = {
            { 1.0, 2.0 },
            null
        };

        assertEquals("Las filas no pueden ser null",
            assertThrows(IllegalArgumentException.class,
                () -> new Matriz(datos)).getMessage());
    }
}
    

