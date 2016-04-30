/*
 * Java Main class where everything is instantiated
 */

class Main
{
    public static void main(String args[])
    {
        BBPlayer player = new BBPlayer();
        try { 
            player.play();
        } catch(Exception e) {
            //oh nooooooo
        }
    }
}
