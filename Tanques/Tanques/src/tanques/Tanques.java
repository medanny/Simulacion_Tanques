package tanques;

import javax.swing.JOptionPane;

/**
 *
 * @author DANIEL LOZANO CARRILLO
 * MEDANNY.COM
 * LLENADO DE TANQUES v0.1
 */
public class Tanques {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    double tanque1=0;
    double tanque2=0;
    double tanque3=0;
    
    double cap1;
    double cap2;
    double cap3;
    
    double caprest;
    double caprest2;
    double caprest3;
    
    
    
   //DEFINIR CAPACIDADES DE TANQUE
    double flujo;
    cap1=Double.parseDouble(JOptionPane.showInputDialog
            ("Cual es la capacidad del tanque 1"));
    cap2= Double.parseDouble(JOptionPane.showInputDialog
            ("Cual es la capacidad del tanque 2"));
    cap3= Double.parseDouble(JOptionPane.showInputDialog
            ("Cual es la capacidad del tanque 3"));
    // DEFINIR SALIDA DE TANQUES
    double salida1=Double.parseDouble(JOptionPane.showInputDialog
            ("Cual es la salida del tanque 1"));;
    double salida2=Double.parseDouble(JOptionPane.showInputDialog
            ("Cual es la salida del tanque 2"));;
    double salida3=Double.parseDouble(JOptionPane.showInputDialog
            ("Cual es la salida del tanque 3"));;
    // DEFINIR FLUJO
    double deflujo=Double.parseDouble(JOptionPane.showInputDialog
            ("Cual es el flujo."));
   int minuto=0;
   System.out.println
         ("Minuto " + " : " + "tanque1" + " : " + "tanque2"+ " : " 
           + "tanque3"); 
   
   //INICIA CICLO HASTA QUE TANQUE 3 ESTE LLENO
   do{
    //autmenta el minuto
    minuto ++; 
    //define el flujo de cada cyclo
    flujo = deflujo;// 
    
    //
    
    
    
    //Calcular capacidad del tanque restante.
    caprest=cap1-tanque1;//10000
    caprest2=cap2-tanque2;//2000
    caprest3=cap3-tanque3;//5000
    
    //calcular las salidas de cada tanque
    if(caprest!=0){
    flujo=flujo-salida1;
    }
    if(caprest==0&&caprest2!=0){
    flujo=flujo-(salida1+salida2);
    }
     if(caprest==0&&caprest2==0){
    flujo=flujo-(salida1+salida2+salida3);
    }
    //advertir si el flujo es menor que la salida.
    if(flujo<=0){
    System.out.println("El flujo es menor que la salida.");
    break;
    }
    
    //llenado tanque 1
    if(caprest>0){
        if(flujo<=caprest){
        tanque1=tanque1+flujo;
        flujo=0;
        }
        if(flujo>caprest){
        tanque1=tanque1+caprest;
        flujo=flujo-caprest;        
        }
    }
   // System.out.println("1 "+flujo);
    
    //llenado tanque 2
    if(flujo!=0){
        if(caprest2>0){
        if(flujo<=caprest2){
        tanque2=tanque2+flujo;
        flujo=0;
        }
        if(flujo>caprest){
        tanque2=tanque2+caprest2;
        flujo=flujo-caprest2;        
        }
    }
    }
     //System.out.println("2 "+flujo);
    // flujo tanque 3
    if(flujo!=0){
    if(caprest3>0){
        if(flujo<=caprest3){
        tanque3=tanque3+flujo;
        flujo=0;
        }
        if(flujo>caprest){
        tanque3=tanque3+caprest3;
        flujo=flujo-caprest3;        
        }
    }
    }
    // System.out.println("3 "+flujo);
    //    
    
    
     
    //imprimir tabla
    System.out.println
            (minuto + "\t : " + tanque1 + " : " + tanque2+ " : " +tanque3);
            
           
    }while(tanque3<cap3);//terminar cyclo
   //alertar que los tanques estan llenos.
   System.out.println("Alerta el tanque esta lleno!"); 

    
    }
}
