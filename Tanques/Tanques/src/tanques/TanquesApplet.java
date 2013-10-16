package tanques;
import java.applet.Applet;
import java.awt.Graphics;
import javax.swing.JOptionPane;
/**
 *
 * @author DANIEL LOZANO CARRILLO
 * MEDANNY.COM
 * LLENADO DE TANQUES v0.2
 * 
 * ChangeLog(cambios)
 * 1. Uso de arreglo bidemencional en lugar de 6 variables.
 * 2. Uso de Applet.
 * 3. El tiempo es especificado en segundos no en minutos.
 * 4. Los tanques son aora simulados en modo grafico y no texto.
 * 5. Se agrego un ratardo despues de cada loop para apreciar la simulacion.
 */
public class TanquesApplet extends Applet implements Runnable {
    int retardo;
    Thread simulacion;
    int llenado;
    private static double[][] tanque = new double[3][5];
    //tanque [0][0] -> Capacidad
    //tanque [0][1] -> Contenido (Cuanto Liquido tiene)
    //tanque [0][2] -> Capacidad restante hasta llenado
    //tanque [0][3] -> Salida cuantos litros salen por minuto.
    //tanque [0][4] -> % del tanque lleno.
    
    double flujo,nuevoflujo;
    int segundo;
    String mensaje;
    String sugerencia;
    
    
    public void init() {
     //inicializar el retardo
        retardo=10;
        mensaje=" ";
        sugerencia=" ";
    }
    public void CapturarDatos(){
    for(int i=0;i<=2;i++){
    //preguntar las entradas y salidas y guardar en arreglo.
    tanque[i][0]=Double.parseDouble(JOptionPane.showInputDialog
            ("Cual es la capacidad del tanque " + (i+1)));
    tanque[i][3]=Double.parseDouble(JOptionPane.showInputDialog
            ("Cual es la salida del tanque " + (i+1)));
    }
    flujo=Double.parseDouble(JOptionPane.showInputDialog
            ("Cual es el flujo por minuto."));
    }
    public void GenerarDatos(){
    //capacidad
        tanque[0][0]=10000;
        tanque[1][0]=5000;
        tanque[2][0]=5000;
        
    // Salida
        tanque[0][3]=1000;
        tanque[1][3]=200;
        tanque[2][3]=500;
        
   // flujo
        flujo=2000;
        
        
    
    }
    public void start() {
        //CapturarDatos();
        GenerarDatos();
        //crear tread 
        if (simulacion == null) {
             simulacion = new Thread(this);
            }
        //iniciar
            simulacion.start();
    }

    public void stop() {
        simulacion=null;
    }
    
    public void run() {
        //minimizar la memoria usada para el tread
        Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
        //lopear
        while (Thread.currentThread() == simulacion) {
            //aumentar segundo
            segundo++;
            //nuevo flujo para cyclo actual
            nuevoflujo=flujo/60;
            
            //calcular capacidad restante
            for(int i=0;i<=2;i++){
            tanque[i][2]=tanque[i][0]-tanque[i][1];
            }
            
            //Calcular Salidas
            if(tanque[0][2]!=0){
            nuevoflujo=nuevoflujo-((tanque [0][3])/60);
            }
            
            if(tanque[0][2]==0&&tanque[1][2]!=0){
             nuevoflujo=nuevoflujo-((tanque[0][3]+tanque[1][3])/60);
            }
            
            if(tanque[0][2]==0&&tanque[1][2]==0){
            nuevoflujo=nuevoflujo-((tanque[0][3]+tanque[1][3]+tanque[2][3])/60);
            }
            
            //advertir si el dlujo es menor que la salida
            if(nuevoflujo<=0){
            mensaje="El flujo es menor que la salida.";
            sugerencia="Porfavor aumente el flujo.";
            break;
            }
            
            //lenado tanques
            
            for(int i=0;i<=2;i++){
                
                if(tanque[i][2]>0){
            
                //si el nuevo flujo es menor que la capacidad restante #1
                if(nuevoflujo<=tanque[i][2]){
                tanque[i][1]=tanque[i][1]+nuevoflujo;
                nuevoflujo=0;
                }
                //si no
                if(nuevoflujo>tanque[i][2]){
                tanque[i][1]=tanque[i][1]+tanque[i][2];
                nuevoflujo=nuevoflujo-tanque[i][2];        
                }
                }
            }
            
            //calcular % de llenado
            for(int i=0;i<=2;i++){
            tanque[i][4]=((tanque[i][1]/tanque[i][0])*100)/2;
            }
           
            
            llenado++;
            //actualizar
            repaint();
            //break
            if(tanque[2][1]>=tanque[2][0]){
                mensaje="Los tanques estan llenos a los " + segundo/60+" minutos.";
                sugerencia="Coloque el flujo a " +(tanque [0][3]+tanque [1][3]+tanque [2][3])+" Litros por minuto.";
            break;
            }
        //Generar RETARDO al terminar cada loop
            try {
                Thread.sleep(retardo);
            } catch (InterruptedException e) {
                break;
            }
        }
        
    }
    //para quitar el flickering pero causa que el texto se sobre ponga.
    //public void update(Graphics g){
      // paint(g);
//}
    
    public void paint(Graphics g) {
       //varables
       g.drawString("Segundo:"+segundo,10,10);
       g.drawString("Entrada: "+flujo,10,25);
       g.drawString("Mensaje: "+mensaje,5,180);
       g.drawString("Sugerencias: "+sugerencia,5,200);
               
       //tanque 1
       g.drawString("Tanque 1:", 49, 49);
       g.drawRect(50, 50, 50, 50);
       g.fillRect(50, 100, 50, (0-(int)tanque[0][4]));
       g.drawString("Cap: " + (int)tanque[0][0],50,111);
       g.drawString("SAlida: " + (int)tanque[0][3],50,126);
       g.drawString("Cont: " + (int)tanque[0][1],50,140);
       g.drawString("Cap Res: " + (int)tanque[0][2],50,155);
       
       //tanque 2
       g.drawString("Tanque 2:", 149, 49);
       g.drawRect(150, 50, 50, 50);
       g.fillRect(150, 100, 50, (0-(int)tanque[1][4]));
       g.drawString("Cap: " + (int)tanque[1][0],150,111);
       g.drawString("SAlida: " + (int)tanque[1][3],150,126);
       g.drawString("Cont: " + (int)tanque[1][1],150,140);
       g.drawString("Cap Res: " + (int)tanque[1][2],150,155);
       
       
       
       //tanque 3
       g.drawString("Tanque 3:", 249, 49);
       g.drawRect(250, 50, 50, 50);
       g.fillRect(250, 100, 50, (0-(int)tanque[2][4]));
       g.drawString("Cap: " + (int)tanque[2][0],250,111);
       g.drawString("SAlida: " + (int)tanque[2][3],250,126);
       g.drawString("Cont: " + (int)tanque[2][1],250,140);
       g.drawString("Cap Res: " + (int)tanque[2][2],250,155);
        
        
        
    }
    
    
}
