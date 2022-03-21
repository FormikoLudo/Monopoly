package controller;

import javafx.scene.image.ImageView;
import javafx.fxml.FXML;

import javafx.scene.control.Label;

/**
 * This class is the controller of the general view of the monopoly
 * board.
 */
public class LittleFieldController {

    /**
     * The parent controller of this controller.
     */
    private FieldController fieldController;

    /*
    Images of the dices.
    */
    @FXML private ImageView de1a;
    @FXML private ImageView de2a;
    @FXML private ImageView de3a;
    @FXML private ImageView de4a;
    @FXML private ImageView de5a;
    @FXML private ImageView de6a;
    @FXML private ImageView de1b;
    @FXML private ImageView de2b;
    @FXML private ImageView de3b;
    @FXML private ImageView de4b;
    @FXML private ImageView de5b;
    @FXML private ImageView de6b;
    @FXML private ImageView de1c;
    @FXML private ImageView de2c;
    @FXML private ImageView de3c;
    @FXML private ImageView de4c;
    @FXML private ImageView de5c;
    @FXML private ImageView de6c;
    @FXML private ImageView de1d;
    @FXML private ImageView de2d;
    @FXML private ImageView de3d;
    @FXML private ImageView de4d;
    @FXML private ImageView de5d;
    @FXML private ImageView de6d;


    /*
    Images of the pawns.
     */
    @FXML private ImageView pawn1; //hat
    @FXML private ImageView pawn2;
    @FXML private ImageView pawn3;
    @FXML private ImageView pawn4;
    @FXML private ImageView pawn5;
    @FXML private ImageView pawn6;

    /*
    A label where is displayed the quotes or the action according to the game.
     */
    @FXML private Label display;

    /**
     * Sets the parent controller to the given controller
     * @param main the new parent controller of this controller
     */
    public void transferController(FieldController main){
	fieldController = main;
    }

    public void init()
    {
      for(int i = 0; i < fieldController.getModel().getPlayers().length; i++)
      {
        movePawn(i,fieldController.getModel().getPlayers()[i].getPawn().getLocation());
      }
      unseeUnplayingPawns();
    }

    /**
     * This method draws the dices according to the result of the dices.
     * @param resultDice The result of the dices.
     */
    public void drawTheDices(int[] resultDice)
    {
      switch (resultDice[0])
      {
        case 1 :
          de1a.setVisible(true);
        break;
        case 2 :
          de2a.setVisible(true);
        break;
        case 3 :
          de3a.setVisible(true);
        break;
        case 4 :
          de4a.setVisible(true);
        break;
        case 5 :
          de5a.setVisible(true);
        break;
        case 6 :
          de6a.setVisible(true);
        break;
      }
      switch (resultDice[1])
      {
        case 1 :
          de1b.setVisible(true);
        break;
        case 2 :
          de2b.setVisible(true);
        break;
        case 3 :
          de3b.setVisible(true);
        break;
        case 4 :
          de4b.setVisible(true);
        break;
        case 5 :
          de5b.setVisible(true);
        break;
        case 6 :
          de6b.setVisible(true);
        break;
      }
      if(resultDice.length > 2)
      {
        switch (resultDice[2])
        {
          case 1 :
          de1c.setVisible(true);
          break;
          case 2 :
          de2c.setVisible(true);
          break;
          case 3 :
          de3c.setVisible(true);
          break;
          case 4 :
          de4c.setVisible(true);
          break;
          case 5 :
          de5c.setVisible(true);
          break;
          case 6 :
          de6c.setVisible(true);
          break;
        }
        if(resultDice.length > 3)
        {
          switch (resultDice[3])
          {
            case 1 :
            de1d.setVisible(true);
            break;
            case 2 :
            de2d.setVisible(true);
            break;
            case 3 :
            de3d.setVisible(true);
            break;
            case 4 :
            de4d.setVisible(true);
            break;
            case 5 :
            de5d.setVisible(true);
            break;
            case 6 :
            de6d.setVisible(true);
            break;
          }
        }
        else
        {
          de1c.setLayoutX(215);
          de2c.setLayoutX(215);
          de3c.setLayoutX(215);
          de4c.setLayoutX(215);
          de5c.setLayoutX(215);
          de6c.setLayoutX(215);
        }
      }
    }

  /**
   * This method makes the dices invisible.
   */
  public void unseeDices()
  {
    de1a.setVisible(false);
    de2a.setVisible(false);
    de3a.setVisible(false);
    de4a.setVisible(false);
    de5a.setVisible(false);
    de6a.setVisible(false);
    de1b.setVisible(false);
    de2b.setVisible(false);
    de3b.setVisible(false);
    de4b.setVisible(false);
    de5b.setVisible(false);
    de6b.setVisible(false);
    de1c.setVisible(false);
    de2c.setVisible(false);
    de3c.setVisible(false);
    de4c.setVisible(false);
    de5c.setVisible(false);
    de6c.setVisible(false);
    de1d.setVisible(false);
    de2d.setVisible(false);
    de3d.setVisible(false);
    de4d.setVisible(false);
    de5d.setVisible(false);
    de6d.setVisible(false);
  }

  /**
   * This method draws a pawn in its location.
   * @param i        The id of the pawn that will be drawn.
   * @param location The location where it must be drawn.
   */
  public void movePawn(int i, int location)
  {
    switch (i)
    {
      case 0 :
        if(location == 0)
        {
          this.pawn1.setLayoutX(435);
          this.pawn1.setLayoutY(495);
        }
        else if(location > 0 && location < 10)
        {
          this.pawn1.setLayoutX(435 - 40*location);
          this.pawn1.setLayoutY(495);
        }
        else if(location == 10)
        {
          if(this.fieldController.getModel().getPlayers()[this.fieldController.getModel().getCurrent()].isInJail())
          {
            putInJail();
          }
          else
          {
            this.pawn1.setLayoutX(0);
            this.pawn1.setLayoutY(465);

          }
        }
        else if(location > 10 && location < 20)
        {
          this.pawn1.setLayoutX(17);
          this.pawn1.setLayoutY(462 - 44*(location - 10));
        }
        else if(location == 20)
        {
          this.pawn1.setLayoutX(45);
          this.pawn1.setLayoutY(10);
        }
        else if(location > 20 && location < 30)
        {
          this.pawn1.setLayoutX(45 + 40*(location - 20));
          this.pawn1.setLayoutY(10);
        }
        else if(location == 30)
        {
          this.pawn1.setLayoutX(480);
          this.pawn1.setLayoutY(48);
        }
        else if(location > 30 && location < 40)
        {
          this.pawn1.setLayoutX(480);
          this.pawn1.setLayoutY(52 + 44*(location - 30));
        }
      break;
      case 1 :
      if(location == 0)
      {
        this.pawn2.setLayoutX(450);
        this.pawn2.setLayoutY(495);
      }
      else if(location > 0 && location < 10)
      {
        this.pawn2.setLayoutX(450 - 40*location);
        this.pawn2.setLayoutY(495);
      }
      else if(location == 10)
      {
        if(this.fieldController.getModel().getPlayers()[this.fieldController.getModel().getCurrent()].isInJail())
        {
          putInJail();
        }
        else
        {
          this.pawn2.setLayoutX(0);
          this.pawn2.setLayoutY(485);
        }
      }
      else if(location > 10 && location < 20)
      {
        this.pawn2.setLayoutX(17);
        this.pawn2.setLayoutY(490 - 44*(location - 10));
      }
      else if(location == 20)
      {
        this.pawn2.setLayoutX(30);
        this.pawn2.setLayoutY(10);
      }
      else if(location > 20 && location < 30)
      {
        this.pawn2.setLayoutX(30 + 40*(location - 20));
        this.pawn2.setLayoutY(10);
      }
      else if(location == 30)
      {
        this.pawn2.setLayoutX(480);
        this.pawn2.setLayoutY(35);
      }
      else if(location > 30 && location < 40)
      {
        this.pawn2.setLayoutX(480);
        this.pawn2.setLayoutY(37 + 44*(location - 30));
      }
      break;
      case 2 :
      if(location == 0)
      {
        this.pawn3.setLayoutX(435);
        this.pawn3.setLayoutY(510);
      }
      else if(location > 0 && location < 10)
      {
        this.pawn3.setLayoutX(435 - 40*location);
        this.pawn3.setLayoutY(510);
      }
      else if(location == 10)
      {
        if(this.fieldController.getModel().getPlayers()[this.fieldController.getModel().getCurrent()].isInJail())
        {
          putInJail();
        }
        else
        {
          this.pawn3.setLayoutX(2);
          this.pawn3.setLayoutY(490);
        }
      }
      else if(location > 10 && location < 20)
      {
        this.pawn3.setLayoutX(2);
        this.pawn3.setLayoutY(462 - 44*(location - 10));
      }
      else if(location == 20)
      {
        this.pawn3.setLayoutX(45);
        this.pawn3.setLayoutY(25);
      }
      else if(location > 20 && location < 30)
      {
        this.pawn3.setLayoutX(45 + 40*(location - 20));
        this.pawn3.setLayoutY(25);
      }
      else if(location == 30)
      {
        this.pawn3.setLayoutX(465);
        this.pawn3.setLayoutY(35);
      }
      else if(location > 30 && location < 40)
      {
        this.pawn3.setLayoutX(465);
        this.pawn3.setLayoutY(52 + 44*(location - 30));
      }
      break;

      case 3 :
      if(location == 0)
      {
        this.pawn4.setLayoutX(450);
        this.pawn4.setLayoutY(510);
      }
      else if(location > 0 && location < 10)
      {
        this.pawn4.setLayoutX(450 - 40*location);
        this.pawn4.setLayoutY(510);
      }
      else if(location == 10)
      {
        if(this.fieldController.getModel().getPlayers()[this.fieldController.getModel().getCurrent()].isInJail())
        {
          putInJail();
        }
        else
        {
          this.pawn4.setLayoutX(20);
          this.pawn4.setLayoutY(515);
        }
      }
      else if(location > 10 && location < 20)
      {
        this.pawn4.setLayoutX(2);
        this.pawn4.setLayoutY(490 - 44*(location - 10));
      }
      else if(location == 20)
      {
        this.pawn4.setLayoutX(30);
        this.pawn4.setLayoutY(25);
      }
      else if(location > 20 && location < 30)
      {
        this.pawn4.setLayoutX(30 + 40*(location - 20));
        this.pawn4.setLayoutY(25);
      }
      else if(location == 30)
      {
        this.pawn4.setLayoutX(465);
        this.pawn4.setLayoutY(48);
      }
      else if(location > 30 && location < 40)
      {
        this.pawn4.setLayoutX(465);
        this.pawn4.setLayoutY(37 + 44*(location - 30));
      }
      break;

      case 4 :
        if(location == 0)
        {
          this.pawn5.setLayoutX(432);
          this.pawn5.setLayoutY(476);
        }
        else if(location > 0 && location < 10)
        {
          this.pawn5.setLayoutX(435 - 40*location);
          this.pawn5.setLayoutY(480);
        }
        else if(location == 10)
        {
          if(this.fieldController.getModel().getPlayers()[this.fieldController.getModel().getCurrent()].isInJail())
          {
            putInJail();
          }
          else
          {
            this.pawn5.setLayoutX(5);
            this.pawn5.setLayoutY(515);
          }
        }
        else if(location > 10 && location < 20)
        {
          this.pawn5.setLayoutX(30);
          this.pawn5.setLayoutY(462 - 44*(location - 10));
        }

        else if(location == 20)
        {
          this.pawn5.setLayoutX(15);
          this.pawn5.setLayoutY(40);
        }

        else if(location > 20 && location < 30)
        {
          this.pawn5.setLayoutX(45 + 40*(location - 20));
          this.pawn5.setLayoutY(37);
        }
        else if(location == 30)
        {
          this.pawn5.setLayoutX(433);
          this.pawn5.setLayoutY(10);
        }
        else if(location > 30 && location < 40)
        {
          this.pawn5.setLayoutX(448);
          this.pawn5.setLayoutY(50 + 44*(location - 30));
        }
      break;


      case 5 :
        if(location == 0)
        {
          this.pawn6.setLayoutX(453);
          this.pawn6.setLayoutY(481);
        }
        else if(location > 0 && location < 10)
        {
          this.pawn6.setLayoutX(450 - 40*location);
          this.pawn6.setLayoutY(480);
        }
        else if(location == 10)
        {
          if(this.fieldController.getModel().getPlayers()[this.fieldController.getModel().getCurrent()].isInJail())
          {
            putInJail();
          }
          else
          {
            this.pawn6.setLayoutX(40);
            this.pawn6.setLayoutY(515);
          }
        }
        else if(location > 10 && location < 20)
        {
          this.pawn6.setLayoutX(30);
          this.pawn6.setLayoutY(490 - 44*(location - 10));
        }

        else if(location == 20)
        {
          this.pawn6.setLayoutX(30);
          this.pawn6.setLayoutY(40);
        }

        else if(location > 20 && location < 30)
        {
          this.pawn6.setLayoutX(30 + 40*(location - 20));
          this.pawn6.setLayoutY(37);
        }

        else if(location == 30)
        {
          this.pawn6.setLayoutX(433);
          this.pawn6.setLayoutY(0);
        }
        else if(location > 30 && location < 40)
        {
          this.pawn6.setLayoutX(448);
          this.pawn6.setLayoutY(38 + 44*(location - 30));
        }
      break;

    }
  }

  /**
   * This method draws the pawn of the current player in jail.
   */
  public void putInJail()
  {
    switch(fieldController.getModel().getCurrent())
    {
      case 0:
        pawn1.setLayoutX(20);
        pawn1.setLayoutY(470);
      break;
      case 1:
        pawn2.setLayoutX(35);
        pawn2.setLayoutY(470);
      break;
      case 2:
        pawn3.setLayoutX(20);
        pawn3.setLayoutY(490);
      break;
      case 3:
        pawn4.setLayoutX(35);
        pawn4.setLayoutY(490);
      break;
      case 4:
        pawn5.setLayoutX(20);
        pawn5.setLayoutY(500);
      break;
      case 5:
        pawn6.setLayoutX(35);
        pawn6.setLayoutY(500);
      break;
    }
  }

  /**
   * This method makes the pawns of the players that has lost invisible.
   */
  public void unseeUnplayingPawns()
  {
    if(!fieldController.getModel().getPlayers()[0].getStillPlaying())
    {
      pawn1.setVisible(false);
    }
    if(!fieldController.getModel().getPlayers()[1].getStillPlaying())
    {
      pawn2.setVisible(false);
    }
    if(!fieldController.getModel().getPlayers()[2].getStillPlaying())
    {
      pawn3.setVisible(false);
    }
    if(!fieldController.getModel().getPlayers()[3].getStillPlaying())
    {
      pawn4.setVisible(false);
    }
    if(fieldController.getModel().getPlayers().length > 4)
    {
      if(!fieldController.getModel().getPlayers()[4].getStillPlaying())
      {
        pawn5.setVisible(false);
      }
      if(fieldController.getModel().getPlayers().length > 5)
      {
        if(!fieldController.getModel().getPlayers()[5].getStillPlaying())
        {
          pawn6.setVisible(false);
        }
      }
      else {
        pawn6.setVisible(false);
      }
    }
    else {
      pawn5.setVisible(false);
      pawn6.setVisible(false);

    }
  }

  /**
   * Getter of the display label.
   * @return The display label.
   */
  public Label getDisplay()
  {
    return this.display;
  }
}
