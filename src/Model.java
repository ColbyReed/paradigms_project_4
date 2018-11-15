import java.util.ArrayList;



class Model
{
    int scrollPos;
    Mario mario;
    ArrayList<Sprite> sprites;
    
	Model()
	{

		mario = new Mario(this);
        sprites = new ArrayList<Sprite>();
        sprites.add(mario);
        //load("map.json");
	}

	void previous_location()
	{
		mario.previous_location();
	}
	public void update(Model m)
	{

		for(int i = 0; i < sprites.size(); i++)
		{
           Sprite s = sprites.get(i);
            boolean alive = s.update(m);
            if(!alive) {
                sprites.remove(i);
                i--;
            }
		}
	}

    void addBrick(int x, int y, int w, int h)
    {
        Sprite s = new Brick(x, y, w, h);
        sprites.add(s);
    }

    void addCoinBlock(int x, int y, int w, int h)
    {
        Sprite s = new CoinBlock(x, y, w, h);
        sprites.add(s);
    }
    
    
    void unmarshall(Json ob)
    {
        sprites.clear();
        Json json_sprites = ob.get("sprites");
        for(int i = 0; i < json_sprites.size(); i++)
        {
            Json j = json_sprites.get(i);
            String str = j.getString("type");
            Sprite s = null;
            if(str.equals("Mario")){
                s = new Mario(j);
                mario = (Mario)s;
            }else if(str.equals("Brick")){
                s = new Brick(j);
            }else if(str.equals("CoinBlock")){
                s = new CoinBlock(j);
            }else if(str.equals("Coin")){
                s = new Coin(j);
            }
            sprites.add(s);
        }
    }
    
    Json marshall()
    {
        Json ob = Json.newObject();
        Json json_sprites = Json.newList();
        ob.add("sprites", json_sprites);
        for(int i = 0; i < sprites.size(); i++)
        {
            Sprite s = sprites.get(i);
            Json j = s.marshall();
            json_sprites.add(j);
        }
        return ob;
    }
    
    void save(String filename)
    {
        Json ob = marshall();
        ob.save(filename);
    }
    
	void load(String filename)
	{
        Json ob = Json.load(filename);
        unmarshall(ob);
	}
    
    
}
