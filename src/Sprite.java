import java.awt.Graphics;

abstract class Sprite
{
    Model model;

    int x;
    int y;
    int w;
    int h;
    double x_vel;
    double y_vel;

    Sprite()
    {

    }

    abstract boolean isMario();
    abstract boolean isBrick();
    abstract boolean isCoinBlock();
    abstract boolean isCoin();

    //abstract boolean doesCollide();

    abstract void draw(Graphics g, Model m);
    abstract boolean update(Model m);
    abstract Json marshall();
}