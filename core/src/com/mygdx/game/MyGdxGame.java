package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import Logic.Map.Map;
import Logic.Unit.Unit;

import static com.badlogic.gdx.Gdx.input;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background = null;
	Texture gridblock = null;
	Sprite blue =null;

	Texture sprite = null;
	TextureRegion[][] sprites = null;
	TextureRegion[] spor = null;

	Sprite spr=null;

	Unit test = null;
	Map map = null;

	private int plx = 0;
	private int ply = 0;

	private int scaleX;
	private int scaleY;

	private OrthographicCamera cam;

	private int stat = 1;

	private void specUpdate(){
		switch(stat){
			case(1):
				stat = 2;
				spr = new Sprite(spor[1]);
				break;
			case(2):
				stat = 3;
				spr = new Sprite(spor[2]);
				break;
			case(3):
				stat = 1;
				spr = new Sprite(spor[0]);
				break;
			default:break;
		}
		return;
	}

	@Override
	public void create () {
		batch = new SpriteBatch();
		background = new Texture("background.jpg");

		gridblock = new Texture("square.png");

		sprite = new Texture("sprite.png");
		sprites = TextureRegion.split(sprite,sprite.getWidth()/3,sprite.getHeight());
		spor = new TextureRegion[3];
		blue = new Sprite(new Texture("blue.png"));
		blue.setOrigin(0f,0f);

		System.arraycopy(sprites[0],0,spor,0,3);
		spr = new Sprite(spor[0]);

		test = new Unit("Test",2,1,1,1,1);
		map = new Map("test",30,30);

		cam = new OrthographicCamera(1280,720);
		cam.position.set((float)plx,(float)ply,0);
		cam.update();

		scaleY = Gdx.graphics.getHeight() /map.height;
		scaleX = Gdx.graphics.getWidth() /map.width;
	}
	double fps;

	@Override
	public void render () {
		batch.setProjectionMatrix(cam.combined);
		this.test.update();
		cam.update();
		fps = Gdx.graphics.getFramesPerSecond();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//Start of Input Section
		//end of input section
		Gdx.input.setCursorCatched(true);

		if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
			Gdx.app.exit();
		}
		if(Gdx.input.isKeyPressed(Input.Keys.UP)){
			if(ply+1<this.map.height){
				ply++;
				cam.translate(0,scaleX,0);
				cam.update();
				batch.setProjectionMatrix(cam.combined);
			}
		}
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
			if(ply-1>=0){
			ply--;
			cam.translate(0,-scaleX,0);
			cam.update();
			batch.setProjectionMatrix(cam.combined);}
		}
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			if(plx-1>=0){
			plx--;
			cam.translate(-scaleX,0,0);
			cam.update();
			batch.setProjectionMatrix(cam.combined);}
		}
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
			if (plx + 1 < this.map.width) {
			plx++;
			cam.translate(scaleX,0,0);
			cam.update();
			batch.setProjectionMatrix(cam.combined);}
		}
		//Start of Logic section

		//End Of Logic Section
		//start of draw section
		batch.begin();

		batch.disableBlending();
		batch.draw(background,-1000,-400);
		batch.enableBlending();

		for(int i=0;i<this.map.height;++i){
			for(int j=0;j<this.map.width;++j){
				batch.draw(gridblock,j*scaleX,i*scaleX,scaleX,scaleX);
			}
		}

		batch.draw(blue,plx*scaleX,ply*scaleX,scaleX,scaleX);
		batch.draw(blue,(plx+1)*scaleX,ply*scaleX,scaleX,scaleX);
		batch.draw(blue,(plx-1)*scaleX,ply*scaleX,scaleX,scaleX);
		batch.draw(blue,plx*scaleX,(ply-1)*scaleX,scaleX,scaleX);
		batch.draw(blue,plx*scaleX,(ply+1)*scaleX,scaleX,scaleX);

		batch.draw(spr,plx*scaleX,ply*scaleX,scaleX,scaleX);
		this.specUpdate();

		batch.end();
		//end of draw section
		Gdx.graphics.setTitle("RPGame FPS:"+fps);
	}

	@Override
	public void dispose () {
		batch.dispose();
		batch.setProjectionMatrix(cam.combined);
		background.dispose();
	}
}