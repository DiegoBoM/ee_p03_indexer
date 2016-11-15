/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ee_p03_indexador;

/**
 *
 * @author Victor Hugo Bolaños
 */

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ArbolB {

    Nodo raiz;
    int cant;
    int altura;

    public ArbolB() {
        raiz = null;
    }

    public boolean insertar(int nuevo) {
        if (raiz == null)
            raiz = new Nodo(nuevo, null);
        else {
            Nodo aux = raiz;
            Nodo padre;
            while (true) {
                if (aux.dato == nuevo)
                    return false;
 
                padre = aux;

                boolean irIzq = aux.dato > nuevo;
                aux = irIzq ? aux.izq : aux.der; //condicion  ? verdadero : falso
                
                if (aux == null) {
                    if (irIzq) {
                        padre.izq = new Nodo(nuevo, padre);
                    } else {
                        padre.der = new Nodo(nuevo, padre);
                    }
                    rebalanceo(padre);
                    break;
                }
            }
        }
        return true;
    }
 
    public void eliminar(int dato) {
        if (raiz == null)
            return;
        Nodo aux = raiz;
        Nodo padre = raiz;
        Nodo delNodo = null;
        Nodo child = raiz;
 
        while (child != null) {
            padre = aux;
            aux = child;
            child = dato >= aux.dato ? aux.der : aux.izq;// condicion  ? verdadero : falso

            if (dato == aux.dato)
                delNodo = aux;
        }
 
        if (delNodo != null) {
            delNodo.dato = aux.dato;
            child = aux.izq != null ? aux.izq : aux.der;// condicion  ? verdadero : falso
 
            if (raiz.dato == dato) {
                raiz = child;
            } else {
                if (padre.izq == aux) {
                    padre.izq = child;
                } else {
                    padre.der = child;
                }
                rebalanceo(padre);
            }
        }
    }
 
    private void rebalanceo(Nodo reba) {
        recibirBalanceo(reba);
 
        if (reba.fe == -2) {
            if (obtenerPeso(reba.izq.izq) >= obtenerPeso(reba.izq.der))
                reba = rotacionIzq(reba);
            else
                reba = rotaCompID(reba);
 
        } else if (reba.fe == 2) {
            if (obtenerPeso(reba.der.der) >= obtenerPeso(reba.der.izq))
                reba = rotacionDer(reba);
            else
                reba = rotaCompDI(reba);
        }
 
        if (reba.padre != null) {
            rebalanceo(reba.padre);
        } else {
            raiz = reba;
        }
    }
    //Rotacion simple a la izquierda
    private Nodo rotacionDer(Nodo rotaIzq) {
        //System.out.println("Rotacion simple a la derecha");
        Nodo aux = rotaIzq.der;
        aux.padre = rotaIzq.padre;
 
        rotaIzq.der = aux.izq;
 
        if (rotaIzq.der != null)
            rotaIzq.der.padre = rotaIzq;
 
        aux.izq = rotaIzq;
        rotaIzq.padre = aux;
 
        if (aux.padre != null) {
            if (aux.padre.der == rotaIzq) {
                aux.padre.der = aux;
            } else {
                aux.padre.izq = aux;
            }
        }
 
        recibirBalanceo(rotaIzq, aux);
 
        return aux;
    }
    //Rotacion simple a la derecha
    private Nodo rotacionIzq(Nodo rotaDer) {
         //System.out.println("Rotacion simple a la izquierda");

        Nodo temp = rotaDer.izq;
        temp.padre = rotaDer.padre;
 
        rotaDer.izq = temp.der;
 
        if (rotaDer.izq != null)
            rotaDer.izq.padre = rotaDer;
 
        temp.der = rotaDer;
        rotaDer.padre = temp;
 
        if (temp.padre != null) {
            if (temp.padre.der == rotaDer) {
                temp.padre.der = temp;
            } else {
                temp.padre.izq = temp;
            }
        }
 
        recibirBalanceo(rotaDer, temp);
 
        return temp;
    }
 
    private Nodo rotaCompID(Nodo rotaCompID) {
        //System.out.println("Rotacion compuesta izquierda a derecha");
        rotaCompID.izq = rotacionDer(rotaCompID.izq);
        return rotacionIzq(rotaCompID);
    }
 
    private Nodo rotaCompDI(Nodo rotaCompDI) {
        //System.out.println("Rotacion compuesta derecha a izquierda");
        rotaCompDI.der = rotacionIzq(rotaCompDI.der);
        return rotacionDer(rotaCompDI);
    }
 
    private int obtenerPeso(Nodo aux) {
        if (aux == null)
            return -1;
        return 1 + Math.max(obtenerPeso(aux.der), obtenerPeso(aux.izq));
    }
 
    private void recibirBalanceo(Nodo... nodes) {
        for (Nodo aux : nodes)
            aux.fe = obtenerPeso(aux.der) - obtenerPeso(aux.izq);
    }
    
    //public JPanel dibujaArbol(ArbolB arb) {
      //  return new Grafico(arb);
    //}

    //public void dibujarArbol() {
      //  JFrame arbolB = new JFrame("Arbol grafico");
        //arbolB.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //arbolB.add(dibujaArbol(arb));
        //arbolB.setSize(600, 800);
        //arbolB.setVisible(true);
    //}

   

    

    public int consigueValor() {

             //Método encargado de verificar que los valores que introduce el usuario
        //sean valores aceptables
        System.out.print("\nvalor: ");
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        try {
            opcion = sc.nextInt();
            return opcion;
        } catch (Exception e) {
            System.out.println("Opcion invalida, introduzca solo valores enteros");
            return consigueValor();
        }

    }

    public void preOrder(Nodo reco) {

          //Método para recorrer el árbol en el sentido preOrder(raíz, izquierda, derecha)
        if (reco != null) {
            System.out.print(reco.dato + " ");
            preOrder(reco.izq);
            preOrder(reco.der);
        }
    }

    public void inOrder(Nodo reco) {

          //Método para recorrer el árbol en el sentido inOrder(izquierda, raíz, derecha)
        if (reco != null) {
            inOrder(reco.izq);
            System.out.print(reco.dato + " ");
            inOrder(reco.der);
        }
    }

    public boolean busqueda(int numero){
     Nodo aux=raiz;
     if(raiz!= null){ 
     while(aux!= null){
         if(aux.getDato()== numero){
         return true;
         }
         else
         if(aux.getDato()< numero){
         aux=aux.getDer();  
         }
         else
         if(aux.getDato()>numero){
          aux=aux.getIzq();   
            }
          }
         }
         return false;
    }

     
    
    
    
    public boolean buscaElemento(int info) {

        //Método encargado de proporcionar la información del nodo solicitado(nivel del nodo, valor de sus nodos
        //, tipo de nodo(rama, hoja)
        //)
        
      	Nodo anterior = null, reco;
        int nivel = 0;
        reco = raiz;
        while (reco != null) {
            nivel++;
            anterior = reco;
            if (info == reco.dato) {
                  	//System.out.println("Nivel en el que se encuentra el nodo: "+(nivel-1));
                //System.out.print("valor izquierdo: \aux");
                if (reco.izq != null) //System.out.println(reco.izq.getDato());
                //System.out.print("valor derecho: \aux");
                {
                    if (reco.der != null) //System.out.println(reco.der.getDato());
                    {
                        if (reco.der == null && reco.izq == null) //System.out.println("nodo de tipo: hoja");
                        //else
                        //System.out.println("Nodo de tipo: rama");
                        //System.out.println("Altura del nodo: "+retornarAltura(reco));
                        {
                            System.out.println("el elemento se encontro");
                            return true;
                            
                        }
                    }
                }
            }
            if (info < reco.dato) {
                reco = reco.izq;
            } else {
                reco = reco.der;
            }

        }
        System.out.println("el elemento no se encontro");
        return false;
    }
}