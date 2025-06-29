package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, shotKeyPressed;
    boolean showDebugText = false;


    public KeyHandler(GamePanel gp) {

        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
     /*   int code = e.getKeyCode();
        GamePanel gp = new GamePanel();*/
 /*       if (gp.gameState == gp.titleState) {
            if (gp.ui.titleScreenState == 0) {
                if (code == KeyEvent.VK_W) {
                    gp.ui.commamdNum--;
                    if (gp.ui.commamdNum < 0) {
                        gp.ui.commamdNum = 2;
                    }
                }
                if (code == KeyEvent.VK_S) {
                    gp.ui.commamdNum++;
                    if (gp.ui.commamdNum > 2) {
                        gp.ui.commamdNum = 0;
                    }
                }
            }

            if (code == KeyEvent.VK_ENTER) {

                if (gp.ui.commamdNum == 0) {
                    gp.gameState = gp.playState;
                    gp.playMusic(0);
                }
                if (gp.ui.commamdNum == 1) {
                    //  add later
                }
                if (gp.ui.commamdNum == 2) {
                    //  add later
                    System.exit(0);
                }
            }
        }
        else if (gp.ui.titleScreenState == 1) {
            if (gp.gameState == gp.playState) {
                if (code == KeyEvent.VK_W) {
                    gp.ui.commamdNum--;
                    if (gp.ui.commamdNum < 0) {
                        gp.ui.commamdNum = 2;
                    }
                }
                if (code == KeyEvent.VK_S) {
                    gp.ui.commamdNum++;
                    if (gp.ui.commamdNum > 2) {
                        gp.ui.commamdNum = 0;
                    }
                }

            if (code == KeyEvent.VK_ENTER) {

                if (gp.ui.commamdNum == 0) {
                    //  gp.gameState = gp.playState;
                    System.out.println("Do Some 法师 specific stuff");
                    gp.playMusic(0);
                }
                if (gp.ui.commamdNum == 1) {
                    System.out.println("Do Some 近卫 specific stuff");
                    gp.playMusic(0);
                    //  add later
                }
                if (gp.ui.commamdNum == 2) {
                    System.out.println("Do Some 医疗 specific stuff");
                    gp.playMusic(0);
                    //  add later
                    gp.playMusic(0);
                }
                if (gp.ui.commamdNum == 3) {
                    System.out.println("Do Some 特种 specific stuff");
                    //  add later
                    gp.playMusic(0);
                }
*/
 /*           }
            if (code == KeyEvent.VK_W) {
                upPressed = true;
            }
            if (code == KeyEvent.VK_S) {
                downPressed = true;
            }
            if (code == KeyEvent.VK_A) {
                leftPressed = true;
            }
            if (code == KeyEvent.VK_D) {
                rightPressed = true;
            }
            }
        }
    }*/
//    @Override
//    public void keyReleased(KeyEvent e) {
//        int code = e.getKeyCode();
//
//        if (code == KeyEvent.VK_W) {
//            upPressed = false;
//        }
//        if (code == KeyEvent.VK_S) {
//            downPressed = false;
//        }
//        if (code == KeyEvent.VK_A) {
//            leftPressed = false;
//        }
//        if (code == KeyEvent.VK_D) {
//            rightPressed = false;
//        }
//
//    }
//}
        int code = e.getKeyCode();
        //*************TITLE STATE**************
        // 这段代码用来处理标题界面箭头的移动的

        if (gp.gameState == gp.titleState) {
            titleState(code);
        }
        //*************PLAYSTATE*******************
        else if (gp.gameState == gp.playState) {
            playState(code);
        }
        //PAUSE STATE
        else if (gp.gameState == gp.pauseState) {
            pauseState(code);
        }
        //DIALOGUE STATE
        else if (gp.gameState == gp.dialogueState) {
            dialogueState(code);
        }
//CHARACTER STATE
        else if (gp.gameState == gp.characterState) {
            characterState(code);
        } else if (gp.gameState == gp.optionState) {
            optionsState(code);
        } else if (gp.gameState == gp.gameOverState) {
            gameOverState(code);
        } else if (gp.gameState == gp.tradeState) {
            tradeState(code);
            //characterState(code);
        }
        else if (gp.gameState == gp.mapState) {
            mapState(code);
        }
    }

    public void mapState(int code) {
        if (code == KeyEvent.VK_M){
            gp.gameState = gp.playState;
        }
    }

    public void tradeState(int code) {

        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }
        if (gp.ui.subState == 0) {
            if (code == KeyEvent.VK_W) {
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 2;
                }
                gp.playSE(9);
            }
            if (code == KeyEvent.VK_S) {
                gp.ui.commandNum++;
                if (gp.ui.commandNum > 2) {
                    gp.ui.commandNum = 0;
                }
                gp.playSE(9);
            }
        }
        if (gp.ui.subState == 1) {
            npcInventory(code);
            if (code == KeyEvent.VK_ESCAPE) {
                gp.ui.subState = 0;
            }
        }
        if (gp.ui.subState == 2) {
            playerInventory(code);
            if (code == KeyEvent.VK_ESCAPE) {
                gp.ui.subState = 0;
            }
        }
    }

    public void gameOverState(int code) {
        if (code == KeyEvent.VK_W) {//如果按W键，则commandNum减1，选项箭头就会向上移动
            gp.ui.commandNum--;
            if (gp.ui.commandNum < 0) {
                gp.ui.commandNum = 1;
            }
            gp.playSE(9);
        }
        if (code == KeyEvent.VK_S) {//如果按S键，则commandNum加1，选项箭头就会向下移动
            gp.ui.commandNum++;
            if (gp.ui.commandNum > 1) {
                gp.ui.commandNum = 0;
            }
            gp.playSE(9);
        }
        if (code == KeyEvent.VK_ENTER) {
            if (gp.ui.commandNum == 0) {
                gp.gameState = gp.playState;
                gp.retry();
                gp.playMusic(0);
            } else if (gp.ui.commandNum == 1) {
                gp.gameState = gp.titleState;
                gp.restart();
            }

        }


    }

    public void optionsState(int code) {//这段代码的作用是，当游戏状态为选项状态时，按下ESC键，
        // 游戏状态会返回到游戏状态，按下ENTER键，游戏状态会返回到游戏状态

        if (code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.playState;
        }
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }
        int maxCommandNum = 0;
        switch (gp.ui.subState) {// 根据当前子状态，设置最大命令数

            case 0:
                maxCommandNum = 5;// 子状态为0时，最大命令数设为5
                break;
            case 3:
                maxCommandNum = 1;//
                break;
        }
        if (code == KeyEvent.VK_W) {

            gp.ui.commandNum--;
            gp.playSE(9);
            if (gp.ui.commandNum < 0) {
                gp.ui.commandNum = maxCommandNum;
            }
        }
        if (code == KeyEvent.VK_S) {
            gp.ui.commandNum++;
            gp.playSE(9);
            if (gp.ui.commandNum > maxCommandNum) {
                gp.ui.commandNum = 0;
            }
        }
        if (code == KeyEvent.VK_A) {
            if (gp.ui.subState == 0) {
                if (gp.ui.commandNum == 1 && gp.music.volumeScale > 0) {
                    gp.music.volumeScale--;
                    gp.music.checkVolume();
                    gp.playSE(9);
                }
            }
            if (gp.ui.commandNum == 2 && gp.se.volumeScale > 0) {
                gp.se.volumeScale--;

                gp.playSE(9);
            }
        }
        if (code == KeyEvent.VK_D) {
            if (gp.ui.subState == 0) {
                if (gp.ui.commandNum == 1 && gp.music.volumeScale < 5) {
                    gp.music.volumeScale++;
                    gp.music.checkVolume();
                    gp.playSE(9);
                }
                if (gp.ui.commandNum == 2 && gp.se.volumeScale < 5) {
                    gp.se.volumeScale++;
                    gp.playSE(9);
                }
            }
        }


    }

    public void titleState(int code) {
       /* if (gp.ui.titleScreenState == 0) {
            if (code == KeyEvent.VK_W) {
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 2;
                }
            }
            if (code == KeyEvent.VK_S) {
                gp.ui.commandNum++;
                if (gp.ui.commandNum > 2) {
                    gp.ui.commandNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                if (gp.ui.commandNum == 0) {
                    gp.playMusic(0);
                    gp.ui.titleScreenState = 1;

                }
                if (gp.ui.commandNum == 1) {
                    //  add later 选择人物属性
                    gp.gameState = gp.playState;
                    gp.playMusic(0);
                }
                if (gp.ui.commandNum == 2) {
                    System.exit(0);
                }
            }

        } else if (gp.ui.titleScreenState == 1) {

            if (code == KeyEvent.VK_W) {
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 3;
                }
            }
            if (code == KeyEvent.VK_S) {
                gp.ui.commandNum++;
                if (gp.ui.commandNum > 3) {
                    gp.ui.commandNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                if (gp.ui.commandNum == 0) {
                    System.out.println("Do some fighter specific stuff");
                    gp.gameState = gp.playState;
                    gp.playMusic(0);
                }
                if (gp.ui.commandNum == 1) {
                    System.out.println("Do some Thief specific stuff");
                    gp.gameState = gp.playState;
                    gp.playMusic(0);
                }
                if (gp.ui.commandNum == 2) {
                    System.out.println("Do some Sorcerer specific stuff");
                    gp.gameState = gp.playState;
                    gp.playMusic(0);
                }
                if (gp.ui.commandNum == 3) {
                    gp.ui.titleScreenState = 0;
                }
            }
        }
*/
        if (code == KeyEvent.VK_W) {
            gp.ui.commandNum--;
            if (gp.ui.commandNum < 0) {
                gp.ui.commandNum = 2;
            }
        }
        if (code == KeyEvent.VK_S) {
            gp.ui.commandNum++;
            if (gp.ui.commandNum > 2) {
                gp.ui.commandNum = 0;
            }
        }
        if (code == KeyEvent.VK_ENTER) {
            if (gp.ui.commandNum == 0) {
                gp.gameState = gp.playState;
                gp.playMusic(0);
            }
            if (gp.ui.commandNum == 1) {
                //  add later 选择人物属性

            }
            if (gp.ui.commandNum == 2) {
                System.exit(0);
            }
        }
    }

    public void playState(int code) {
        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if (code == KeyEvent.VK_P) {
            if (gp.gameState == gp.playState) {
                gp.gameState = gp.pauseState;
                gp.stopMusic();  // 进入暂停状态时停止音乐
            } else if (gp.gameState == gp.pauseState) {
                gp.gameState = gp.playState;
                gp.playMusic(0);  // 退出暂停状态时重新播放音乐
            }
        }
        if (code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.optionState;
        }
        if (code == KeyEvent.VK_F) {
            shotKeyPressed = true;
        }
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }
        if (code == KeyEvent.VK_C) {//
            if (gp.gameState == gp.playState) {
                gp.gameState = gp.characterState;
            } else if (gp.gameState == gp.characterState) {
                gp.gameState = gp.playState;
            }
        }
        if (code == KeyEvent.VK_M){
            gp.gameState = gp.mapState;
        }
        if (code == KeyEvent.VK_X){
            if (gp.map.miniMapOn == false){
                gp.map.miniMapOn = true;
            }else{
                gp.map.miniMapOn = false;
            }


        }


        if (code == KeyEvent.VK_T) {
            if (showDebugText == false) {
                showDebugText = true;

            } else if (showDebugText == true) {
                showDebugText = false;
            }
        }

        if (code == KeyEvent.VK_R) {
            switch (gp.currentMap) {
                case 0:
                    gp.tileM.loadMap("/maps/worldV3.txt", 0);
                    break;
                case 1:
                    gp.tileM.loadMap("/maps/interior01.txt", 1);
                    break;
            }
        }

    }

    public void pauseState(int code) {
        if (code == KeyEvent.VK_P) {
            gp.gameState = gp.playState;
            gp.playMusic(gp.currentMusic);
        }
    }

    public void dialogueState(int code) {
        if (code == KeyEvent.VK_ENTER) {
            //gp.gameState = gp.playState;

            enterPressed = true;
        }
    }

    public void characterState(int code) {
        if (code == KeyEvent.VK_C) {
            gp.gameState = gp.playState;
        }
        if (code == KeyEvent.VK_ENTER) {//选择物品
            gp.player.selectItem();
        }
        playerInventory(code);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        if (code == KeyEvent.VK_F) {
            shotKeyPressed = false;
        }
    }

    public void playerInventory(int code) {
        if (code == KeyEvent.VK_W) {
            if (gp.ui.playerSlotRow != 0) {
                gp.ui.playerSlotRow--;
                gp.playSE(9);
            }
        }
        if (code == KeyEvent.VK_A) {
            if (gp.ui.playerSlotCol != 0) {
                gp.ui.playerSlotCol--;
                gp.playSE(9);
            }
        }
        if (code == KeyEvent.VK_S) {
            if (gp.ui.playerSlotRow != 3) {
                gp.ui.playerSlotRow++;
                gp.playSE(9);
            }
        }
        if (code == KeyEvent.VK_D) {
            if (gp.ui.playerSlotCol != 4) {
                gp.ui.playerSlotCol++;
                gp.playSE(9);
            }
        }
        if (code == KeyEvent.VK_ENTER) {//选择物品
            gp.player.selectItem();
        }
    }

    public void npcInventory(int code) {
        if (code == KeyEvent.VK_W) {
            if (gp.ui.npcSlotRow != 0) {
                gp.ui.npcSlotRow--;
                gp.playSE(9);
            }
        }
        if (code == KeyEvent.VK_A) {
            if (gp.ui.npcSlotCol != 0) {
                gp.ui.npcSlotCol--;
                gp.playSE(9);
            }
        }
        if (code == KeyEvent.VK_S) {
            if (gp.ui.npcSlotRow != 3) {
                gp.ui.npcSlotRow++;
                gp.playSE(9);
            }
        }
        if (code == KeyEvent.VK_D) {
            if (gp.ui.npcSlotCol != 4) {
                gp.ui.npcSlotCol++;
                gp.playSE(9);
            }
        }

    }
}