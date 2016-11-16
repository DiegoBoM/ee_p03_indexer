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
public class Nodo {

	Nodo der;
	Nodo izq;
        Nodo padre;
	int dato;
        String val="";

    public String getValor() {
        return val;
    }

    public void setValor(String valor) {
        this.val= valor;
    }
        int fe;
        int peso;
	boolean root;

 
        public Nodo(int num, Nodo p,String valor) {
            dato = num;
            padre = p;
            val=valor;
        }
        
	public Nodo(){
		this.izq = null;
		this.der = null;
                this.dato = 0;
                this.fe = 0;
	}
        
        public Nodo(int d,String valor){
		this.izq = null;
		this.der = null;
                this.dato = d;
                this.fe = 1;
                this.peso = 0;
                this.val=valor;
	}


	public void setDato(int dato){
		this.dato = dato;
	}

	public int getDato(){
		return this.dato;
	}

	public void setDer(Nodo nodo){
		this.der = nodo;
	}

	public Nodo getDer(){
		return this.der;
	}

	public void setIzq(Nodo nodo){
		this.izq = nodo;
	}

	public Nodo getIzq(){
		return this.izq;
	}
}
