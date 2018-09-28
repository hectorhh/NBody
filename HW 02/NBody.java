/* Name: Hector Herrera
 * Pennkey: Hectorh
 * Recitation: 216
 * 
 * Execution: Java NBody
 * 
 * DESCRIPTION: The planets will rotate around the solar system 
 * 
 */
public class NBody {
    public static void main(String[] args) {
        
        //three command-line arguments
        double simulationTime = Double.parseDouble(args[0]);
        double timeStep = Double.parseDouble(args[1]);
        String filename = args[2];
        
        //creates a variable inStream to read from a file
        In inStream = new In(filename);
        int numParticles = inStream.readInt();
        double radius = inStream.readDouble();
         
        //declare and initialise the arrays
        double [] m = new double [numParticles];
        double [] px = new double [numParticles];
        double [] py = new double [numParticles];
        double [] vx = new double [numParticles];
        double [] vy = new double [numParticles];
        String [] img = new String [numParticles];
        
        //use an instream to open file
        new In("solarSystem.txt");
        //System.out.println("opens the file");
        
        //loop function to call values in numParticles
        for (int i = 0; i < numParticles; i++) {
            m[i] = inStream.readDouble();
            px[i] = inStream.readDouble();
            py[i] = inStream.readDouble();
            vx[i] = inStream.readDouble();
            vy[i] = inStream.readDouble();
            img[i] = inStream.readString();
        }
        
        //background music
        StdAudio.play("2001.mid");
        
        //enable animation at 30 fps
        PennDraw.enableAnimation(30);
        
        //Time Loop
        for (double t = 0; t < simulationTime; t = t + timeStep) {
            //Set the coordinates of the simulation window
            PennDraw.setXscale(-radius, radius);
            PennDraw.setYscale(-radius, radius);
        
            //draw starfield background
            PennDraw.picture(0, 0, "starfield.jpg");
           
            //
            for (int a = 0; a < numParticles; a++) {
                for (int b = 0; b < numParticles; b++) {
                    if (!(a == b)) {
                    double deltaX = px[b] - px[a];
                    double deltaY = py[b] - py[a];
                    double dist = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
                    double G = 6.67e-11;
                    
                    //force
                    double force = ((G * m[a]) / (dist * dist)) * m[b];
                    
                    //change of force
                    double changeFx = force * deltaX / dist;
                    double changeFy = force * deltaY / dist;
                    
                    //acceleration
                    double accelX = changeFx / m[a];
                    double accelY = changeFy / m[a];
                    
                    //update the parcticles velocities
                    vx[a] = vx[a] + accelX * timeStep;
                    vy[a] = vy[a] + accelY * timeStep; 
                    }
                }                
            }
       
            //Update the particles positions
            for (int i = 0; i < numParticles; i++) {
                px[i] = px[i] + vx [i] * timeStep;
                py[i] = py[i] + vy [i] * timeStep;
            }
        
            //Draw the particles
            for (int i = 0; i < numParticles; i++) {
                PennDraw.picture(px[i], py[i], img [i]);
            }
            PennDraw.advance(); 
        }
        
        //Checkpoint code
        System.out.printf("%d\n", numParticles);
        System.out.printf("%.2e\n", radius);
        for (int i = 0; i < numParticles; i++) {
            System.out.printf("%12.5e %12.5e %12.5e %12.5e %12.5e %12s\n", 
                              m[i], px[i], py[i], vx[i], vy[i], img[i]);
        }   
    }
}

















