package game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import game.GraphicsComponent.Character;
import main.game.InputHandler.GameHandler;

public class PlayState extends State
{
    private SpriteBatch batch = null;
    private Texture background = null;
    private Texture gridBlock = null;
    private main.game.InputHandler.MovementDelta response = null;

    private Character character=null;
    private main.Logic.Map.Map map = new main.Logic.Map.Map("test",30,30);

    private OrthographicCamera cam;

    private int Scale = 0;

    public PlayState(GameStateManager gsm)
    {
        super(gsm);
        this.Scale = Gdx.graphics.getWidth()/this.map.width;
        batch = new SpriteBatch();
        background = new Texture("background.jpg");
        gridBlock = new Texture("square.png");
        character = new Character(this.batch);

        this.cam = new OrthographicCamera(1280,720);
        cam.update();

        this.character.getUnit().setPosition(this.map.getCell(10,10));
        character.update();
        //gameHandler = new GameHandler(this);

        this.cam.position.set(this.character.getUnit().getX()*Scale,this.character.getUnit().getY()*Scale,0);
        cam.update();

        Gdx.input.setCursorCatched(false);
    }

    @Override
    protected void handleInput()
    {
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP)){
            this.character.moveUp();
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN)){
            this.character.moveDown();
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT)){
            this.character.moveLeft();
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            this.character.moveRight();
        }

        this.cam.position.set(this.character.getUnit().getX()*Scale,this.character.getUnit().getY()*Scale,0);
        cam.update();
        batch.setProjectionMatrix(cam.combined);
    }

    @Override
    public void update(float dt)
    {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb)
    {
        sb = batch;
        sb.setProjectionMatrix(cam.combined);
        cam.update();
        fps = Gdx.graphics.getFramesPerSecond();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //Start of Input Section

        //end of input section
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            Gdx.app.exit();
        }
        //this.response = this.handleInput();

      /* if(response!=null){
            if(response.deltaY==1){
                this.character.moveUp();
            }

            if(response.deltaY==-1){
                this.character.moveDown();
            }

            if(response.deltaX==-1){
                this.character.moveLeft();
            }

            if(response.deltaX==1){
                this.character.moveRight();
            }

            this.cam.position.set(this.character.getUnit().getX()*Scale,this.character.getUnit().getY()*Scale,0);
            cam.update();
            batch.setProjectionMatrix(cam.combined);
        } */

        //Start of Logic section

        //End Of Logic Section
        //start of draw section
        sb.begin();

        sb.disableBlending();
        sb.draw(background,-1000,-400);
        sb.enableBlending();

        for(int i=0;i<this.map.height;++i){
            for(int j=0;j<this.map.width;++j){
                batch.draw(this.gridBlock,i*Scale,j*Scale,Scale,Scale);
            }
        }

        sb.draw(this.character.getSprite(),this.character.getUnit().getX()*Scale,
                this.character.getUnit().getY()*Scale,Scale,Scale);

        sb.end();
        //end of draw section
        Gdx.graphics.setTitle("RPGame FPS:"+fps);
    }

    double fps;

    @Override
    public void dispose () {
        batch.dispose();
        background.dispose();
        gridBlock.dispose();
    }
}
