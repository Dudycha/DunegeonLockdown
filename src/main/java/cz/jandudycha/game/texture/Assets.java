package cz.jandudycha.game.texture;

import java.awt.*;
import java.awt.image.BufferedImage;


public class Assets {
    private static final int x = 32, y = 32;

    public static Font coinFont = new Font("coinFont", Font.BOLD, 22);
    public static Font gunFont = new Font("gunFont", Font.BOLD, 17);
    public static Font smallFont = new Font("smallFont", Font.BOLD, 12);
    public static Font mediumFont = new Font("mediumFont", Font.BOLD, 16);
    public static Font bigFont = new Font("bigFont", Font.BOLD, 47);


    public static BufferedImage player, srdce, apple, pulSrdce, medkit, bulletImpact, bullet9mm, bullet556, bullet50EA, gaugeBuckshot, parachute, titleImage, coins, coin, goldNuget, goldBar;
    public static BufferedImage pavoukRight, pavoukLeft;
    public static BufferedImage backround, BorderFrame1000x550, middleSlabChains, backroundHit, menuFrame, menu_inGame, buy_menu, menu_end_Game, menu_scoreBoard, menu_settings;
    public static BufferedImage SMGRight, SMGUp, SMGLeft, SMGDown, SMGUpRight, SMGUpLeft, SMGDownRight, SMGDownLeft;
    public static BufferedImage pistolRight, pistolUp, pistolLeft, pistolDown, pistolUpRight, pistolUpLeft, pistolDownRight, pistolDownLeft;
    public static BufferedImage AKRight, AKUp, AKLeft, AKDown, AKUpRight, AKUpLeft, AKDownRight, AKDownLeft;
    public static BufferedImage ShotgunRight, ShotgunUp, ShotgunLeft, ShotgunDown, ShotgunUpRight, ShotgunUpLeft, ShotgunDownRight, ShotgunDownLeft;
    public static BufferedImage desertEagleRight, desertEagleUp, desertEagleLeft, desertEagleDown, desertEagleUpRight, desertEagleUpLeft, desertEagleDownRight, desertEagleDownLeft;
    public static BufferedImage ammoBox9mm, ammoBoxSMG, ammoBoxBuckShot, ammoBox556, ammoBox50EA, goldnugetBox, goldBarBox, fireBall;

    public static BufferedImage[] pavoukAttackRight = new BufferedImage[2];
    public static BufferedImage[] pavoukAttackLeft = new BufferedImage[2];

    public static BufferedImage[] skull = new BufferedImage[4];
    public static BufferedImage[] victoryPicture = new BufferedImage[9];

    public static BufferedImage[] swordMainPicture = new BufferedImage[5];
    public static BufferedImage[] pistol1911MainPicture = new BufferedImage[2];
    public static BufferedImage[] SMGMainPicture = new BufferedImage[4];
    public static BufferedImage[] shoutgunMainPicture = new BufferedImage[6];
    public static BufferedImage[] AK_47MainPictureSmall = new BufferedImage[4];
    public static BufferedImage[] AK_47MainPictureBig = new BufferedImage[10];
    public static BufferedImage[] desertEagleMainPicture = new BufferedImage[2];

    public static BufferedImage[] btnStart = new BufferedImage[2];
    public static BufferedImage[] btnScoreBoard = new BufferedImage[2];
    public static BufferedImage[] btnBack = new BufferedImage[2];
    public static BufferedImage[] btnQuit = new BufferedImage[2];
    public static BufferedImage[] btnSetings = new BufferedImage[2];
    public static BufferedImage[] btnRestart = new BufferedImage[2];
    public static BufferedImage[] btnMenu = new BufferedImage[2];
    public static BufferedImage[] btnResume = new BufferedImage[2];
    public static BufferedImage[] btnBuy = new BufferedImage[2];
    public static BufferedImage[] btnCross = new BufferedImage[2];
    public static BufferedImage[] btnClearData = new BufferedImage[2];

    public static BufferedImage[] smallWorm = new BufferedImage[3];
    public static BufferedImage[] smallWormDeadAnimation = new BufferedImage[3];
    public static BufferedImage[] spiderDeathAnimation = new BufferedImage[4];

    public static BufferedImage[] skeletonRightUpperPart = new BufferedImage[4];
    public static BufferedImage[] skeletonRightLowerPart = new BufferedImage[4];
    public static BufferedImage[] skeletonLeftUpperPart = new BufferedImage[4];
    public static BufferedImage[] skeletonLeftLowerPart = new BufferedImage[4];

    public static BufferedImage[][] skeletonRightAttackAnimation = new BufferedImage[4][2];
    public static BufferedImage[][] skeletonLeftAttackAnimation = new BufferedImage[4][2];

    public static BufferedImage[][] bullRight = new BufferedImage[6][9];
    public static BufferedImage[][] bullLeft = new BufferedImage[6][9];
    public static BufferedImage[][] bullTurning = new BufferedImage[8][9];
    public static BufferedImage[][] bullAttackRight = new BufferedImage[6][9];
    public static BufferedImage[][] bullAttackLeft = new BufferedImage[6][9];
    public static BufferedImage[][] bullDeathRight = new BufferedImage[6][9];
    public static BufferedImage[][] bullDeathLeft = new BufferedImage[6][9];

    public static BufferedImage[][] goblinRight = new BufferedImage[4][2];
    public static BufferedImage[][] goblinLeft = new BufferedImage[4][2];
    public static BufferedImage[][] goblinAttackRight = new BufferedImage[7][2];
    public static BufferedImage[][] goblinAttackLeft = new BufferedImage[7][2];
    public static BufferedImage[][] goblinDeathRight = new BufferedImage[7][2];
    public static BufferedImage[][] goblinDeathLeft = new BufferedImage[7][2];

    public static BufferedImage[] dragonMovingRight = new BufferedImage[8];
    public static BufferedImage[] dragonMovingLeft = new BufferedImage[8];
    public static BufferedImage[] dragonAttackRight = new BufferedImage[8];
    public static BufferedImage[] dragonAttackLeft = new BufferedImage[8];
    public static BufferedImage[] dragonTurningRight = new BufferedImage[6];
    public static BufferedImage[] dragonTurningLeft = new BufferedImage[6];
    public static BufferedImage[] dragonDeathRight = new BufferedImage[23];
    public static BufferedImage[] dragonDeathLeft = new BufferedImage[23];
    public static BufferedImage[] dragonFireballRight = new BufferedImage[8];
    public static BufferedImage[] dragonFireballLeft = new BufferedImage[8];

    public static BufferedImage[][] skeletonRightDeathAnimation = new BufferedImage[2][6];
    public static BufferedImage[][] skeletonLeftDeathAnimation = new BufferedImage[2][6];

    public static BufferedImage[][] dungeonDoor = new BufferedImage[7][4];
    public static BufferedImage[] moleHill = new BufferedImage[12];

    public static BufferedImage[] playerMoveRight = new BufferedImage[2];
    public static BufferedImage[] playerMoveLeft = new BufferedImage[2];
    public static BufferedImage[] playerRightIdle = new BufferedImage[8];
    public static BufferedImage[] playerleftIdle = new BufferedImage[8];
    public static BufferedImage[] swordRight = new BufferedImage[10];
    public static BufferedImage[] swordLeft = new BufferedImage[10];
    public static BufferedImage[] trubkaZavrena = new BufferedImage[4];
    public static BufferedImage[] trubkaOtevrena = new BufferedImage[4];
    public static BufferedImage[] trubkaSOcima = new BufferedImage[4];

    public static void init() {

        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png"), 32, 32);
        SpriteSheet dragonTexturesSheet = new SpriteSheet(ImageLoader.loadImage("/textures/dragon_textures.png"), 160, 128);
        backround = ImageLoader.loadImage("/textures/backround.png");
        backroundHit = ImageLoader.loadImage("/textures/backroundHit.png");
        BorderFrame1000x550 = ImageLoader.loadImage("/textures/borderFrame1000x550.png");
        menuFrame = ImageLoader.loadImage("/textures/menu.png");
        menu_scoreBoard = ImageLoader.loadImage("/textures/menu_ScoreBoard.png");
       menu_settings = ImageLoader.loadImage("/textures/menu_settings.png");
        menu_inGame = ImageLoader.loadImage("/textures/menu_InGame.png");
        buy_menu = ImageLoader.loadImage("/textures/buy_Menu.png");
        middleSlabChains = ImageLoader.loadImage("/textures/middle_slab_chains.png");
        menu_end_Game = ImageLoader.loadImage("/textures/menu_end_game.png");

        cropDragon(dragonTexturesSheet);

        titleImage = sheet.crop(0, 0);
        srdce = sheet.crop(x * 2, y);
        apple = sheet.crop(x * 4, y * 2);
        pulSrdce = sheet.crop(x, y);
        medkit = sheet.crop(x * 6, y);
        player = sheet.crop(x * 4, y * 4);
        pavoukRight = sheet.crop(x, y * 6);
        pavoukLeft = sheet.crop(0, y * 6);
        bulletImpact = sheet.crop(x * 3, y * 2);
        bullet9mm = sheet.crop(x * 8, y * 3);
        bullet556 = sheet.crop(x * 10, y * 3);
        gaugeBuckshot = sheet.crop(x * 10, y * 4);
        bullet50EA = sheet.crop(x * 11, y * 3);
        parachute = sheet.crop(x * 4, y);
        coins = sheet.crop(x * 5, y);
        coin = sheet.crop(x * 5, y * 2);
        goldNuget = sheet.crop(x * 5, 0);
        goldBar = sheet.crop(x * 4, 0);
        goldnugetBox = sheet.crop(x * 6, 0);
        goldBarBox = sheet.crop(x * 3, 0);
        fireBall = sheet.crop(x * 2, y * 2);

        ammoBox9mm = sheet.crop(x * 17, y * 9);
        ammoBoxSMG = sheet.crop(x * 17, y * 10);
        ammoBoxBuckShot = sheet.crop(x * 17, y * 11);
        ammoBox556 = sheet.crop(x * 17, y * 12);
        ammoBox50EA = sheet.crop(x * 17, y * 13);

        pavoukAttackLeft[0] = sheet.crop(0, y * 6);
        pavoukAttackLeft[1] = sheet.crop(0, y * 7);

        pavoukAttackRight[0] = sheet.crop(x, y * 6);
        pavoukAttackRight[1] = sheet.crop(x, y * 7);

        smallWorm[0] = sheet.crop(x * 4, y * 6);
        smallWorm[1] = sheet.crop(x * 5, y * 6);
        smallWorm[2] = sheet.crop(x * 6, y * 6);

        moleHill[0] = sheet.crop(0, y * 5);
        moleHill[1] = sheet.crop(x, y * 5);
        moleHill[2] = sheet.crop(x * 2, y * 5);
        moleHill[3] = sheet.crop(x * 3, y * 5);
        moleHill[4] = sheet.crop(x * 4, y * 5);
        moleHill[5] = sheet.crop(x * 5, y * 5);
        moleHill[6] = sheet.crop(x * 6, y * 5);
        moleHill[7] = sheet.crop(x * 7, y * 5);
        moleHill[8] = sheet.crop(x * 8, y * 5);
        moleHill[9] = sheet.crop(x * 9, y * 5);
        moleHill[10] = sheet.crop(x * 10, y * 5);
        moleHill[11] = sheet.crop(x * 11, y * 5);

        smallWormDeadAnimation[0] = sheet.crop(x * 4, y * 7);
        smallWormDeadAnimation[1] = sheet.crop(x * 5, y * 7);
        smallWormDeadAnimation[2] = sheet.crop(x * 6, y * 7);

        spiderDeathAnimation[0] = sheet.crop(x * 2, y * 6);
        spiderDeathAnimation[1] = sheet.crop(x * 3, y * 6);
        spiderDeathAnimation[2] = sheet.crop(x * 2, y * 7);
        spiderDeathAnimation[3] = sheet.crop(x * 3, y * 7);

        skull[0] = sheet.crop(x * 7, y * 6);
        skull[1] = sheet.crop(x * 8, y * 6);
        skull[2] = sheet.crop(x * 7, y * 7);
        skull[3] = sheet.crop(x * 8, y * 7);

        victoryPicture[0] = sheet.crop(x * 9, y * 6);
        victoryPicture[1] = sheet.crop(x * 10, y * 6);
        victoryPicture[2] = sheet.crop(x * 11, y * 6);
        victoryPicture[3] = sheet.crop(x * 9, y * 7);
        victoryPicture[4] = sheet.crop(x * 10, y * 7);
        victoryPicture[5] = sheet.crop(x * 11, y * 7);
        victoryPicture[6] = sheet.crop(x * 9, y * 8);
        victoryPicture[7] = sheet.crop(x * 10, y * 8);
        victoryPicture[8] = sheet.crop(x * 11, y * 8);


        pistolRight = sheet.crop(x * 8, y * 9);
        pistolUp = sheet.crop(x * 11, y * 9);
        pistolLeft = sheet.crop(x * 10, y * 9);
        pistolDown = sheet.crop(x * 9, y * 9);
        pistolUpRight = sheet.crop(x * 12, y * 9);
        pistolUpLeft = sheet.crop(x * 15, y * 9);
        pistolDownRight = sheet.crop(x * 13, y * 9);
        pistolDownLeft = sheet.crop(x * 14, y * 9);

        SMGRight = sheet.crop(x * 8, y * 10);
        SMGUp = sheet.crop(x * 11, y * 10);
        SMGLeft = sheet.crop(x * 10, y * 10);
        SMGDown = sheet.crop(x * 9, y * 10);
        SMGUpRight = sheet.crop(x * 12, y * 10);
        SMGUpLeft = sheet.crop(x * 15, y * 10);
        SMGDownRight = sheet.crop(x * 13, y * 10);
        SMGDownLeft = sheet.crop(x * 14, y * 10);

        ShotgunRight = sheet.crop(x * 8, y * 11);
        ShotgunUp = sheet.crop(x * 11, y * 11);
        ShotgunLeft = sheet.crop(x * 10, y * 11);
        ShotgunDown = sheet.crop(x * 9, y * 11);
        ShotgunUpRight = sheet.crop(x * 12, y * 11);
        ShotgunUpLeft = sheet.crop(x * 15, y * 11);
        ShotgunDownRight = sheet.crop(x * 13, y * 11);
        ShotgunDownLeft = sheet.crop(x * 14, y * 11);

        AKRight = sheet.crop(x * 8, y * 12);
        AKUp = sheet.crop(x * 11, y * 12);
        AKLeft = sheet.crop(x * 10, y * 12);
        AKDown = sheet.crop(x * 9, y * 12);
        AKUpRight = sheet.crop(x * 12, y * 12);
        AKUpLeft = sheet.crop(x * 15, y * 12);
        AKDownRight = sheet.crop(x * 13, y * 12);
        AKDownLeft = sheet.crop(x * 14, y * 12);

        desertEagleRight = sheet.crop(x * 8, y * 13);
        desertEagleUp = sheet.crop(x * 11, y * 13);
        desertEagleLeft = sheet.crop(x * 10, y * 13);
        desertEagleDown = sheet.crop(x * 9, y * 13);
        desertEagleUpRight = sheet.crop(x * 12, y * 13);
        desertEagleUpLeft = sheet.crop(x * 15, y * 13);
        desertEagleDownRight = sheet.crop(x * 13, y * 13);
        desertEagleDownLeft = sheet.crop(x * 14, y * 13);


        swordMainPicture[0] = sheet.crop(x * 7, 0);
        swordMainPicture[1] = sheet.crop(x * 8, 0);
        swordMainPicture[2] = sheet.crop(x * 9, 0);
        swordMainPicture[3] = sheet.crop(x * 10, 0);
        swordMainPicture[4] = sheet.crop(x * 11, 0);

        pistol1911MainPicture[0] = sheet.crop(x * 14, y * 2);
        pistol1911MainPicture[1] = sheet.crop(x * 15, y * 2);

        SMGMainPicture[0] = sheet.crop(x * 16, y * 2);
        SMGMainPicture[1] = sheet.crop(x * 17, y * 2);
        SMGMainPicture[2] = sheet.crop(x * 18, y * 2);
        SMGMainPicture[3] = sheet.crop(x * 19, y * 2);

        AK_47MainPictureSmall[0] = sheet.crop(x * 16, 0);
        AK_47MainPictureSmall[1] = sheet.crop(x * 17, 0);
        AK_47MainPictureSmall[2] = sheet.crop(x * 18, 0);
        AK_47MainPictureSmall[3] = sheet.crop(x * 19, 0);

        AK_47MainPictureBig[0] = sheet.crop(x * 15, y * 3);
        AK_47MainPictureBig[1] = sheet.crop(x * 16, y * 3);
        AK_47MainPictureBig[2] = sheet.crop(x * 17, y * 3);
        AK_47MainPictureBig[3] = sheet.crop(x * 18, y * 3);
        AK_47MainPictureBig[4] = sheet.crop(x * 19, y * 3);
        AK_47MainPictureBig[5] = sheet.crop(x * 15, y * 4);
        AK_47MainPictureBig[6] = sheet.crop(x * 16, y * 4);
        AK_47MainPictureBig[7] = sheet.crop(x * 17, y * 4);
        AK_47MainPictureBig[8] = sheet.crop(x * 18, y * 4);
        AK_47MainPictureBig[9] = sheet.crop(x * 19, y * 4);

        shoutgunMainPicture[0] = sheet.crop(x * 14, y);
        shoutgunMainPicture[1] = sheet.crop(x * 15, y);
        shoutgunMainPicture[2] = sheet.crop(x * 16, y);
        shoutgunMainPicture[3] = sheet.crop(x * 17, y);
        shoutgunMainPicture[4] = sheet.crop(x * 18, y);
        shoutgunMainPicture[5] = sheet.crop(x * 19, y);

        desertEagleMainPicture[0] = sheet.crop(x * 14, 0);
        desertEagleMainPicture[1] = sheet.crop(x * 15, 0);

        swordRight[0] = sheet.crop(x * 7, y);
        swordRight[1] = sheet.crop(x * 8, y);
        swordRight[2] = sheet.crop(x * 9, y);
        swordRight[3] = sheet.crop(x * 10, y);
        swordRight[4] = sheet.crop(x * 11, y);
        swordRight[5] = sheet.crop(x * 12, y);
        swordRight[6] = sheet.crop(x * 13, y);
        swordRight[7] = sheet.crop(x * 13, y);
        swordRight[8] = sheet.crop(x * 13, y);
        swordRight[9] = sheet.crop(x * 13, y);

        swordLeft[0] = sheet.crop(x * 13, y * 2);
        swordLeft[1] = sheet.crop(x * 13, y * 2);
        swordLeft[2] = sheet.crop(x * 13, y * 2);
        swordLeft[3] = sheet.crop(x * 13, y * 2);
        swordLeft[4] = sheet.crop(x * 12, y * 2);
        swordLeft[5] = sheet.crop(x * 11, y * 2);
        swordLeft[6] = sheet.crop(x * 10, y * 2);
        swordLeft[7] = sheet.crop(x * 9, y * 2);
        swordLeft[8] = sheet.crop(x * 8, y * 2);
        swordLeft[9] = sheet.crop(x * 7, y * 2);

        skeletonRightUpperPart[0] = sheet.crop(0, y * 8);
        skeletonRightUpperPart[1] = sheet.crop(x, y * 8);
        skeletonRightUpperPart[2] = sheet.crop(x * 2, y * 8);
        skeletonRightUpperPart[3] = sheet.crop(x, y * 8);
        skeletonRightLowerPart[0] = sheet.crop(0, y * 9);
        skeletonRightLowerPart[1] = sheet.crop(x, y * 9);
        skeletonRightLowerPart[2] = sheet.crop(x * 2, y * 9);
        skeletonRightLowerPart[3] = sheet.crop(x, y * 9);

        skeletonLeftUpperPart[0] = sheet.crop(0, y * 10);
        skeletonLeftUpperPart[1] = sheet.crop(x, y * 10);
        skeletonLeftUpperPart[2] = sheet.crop(x * 2, y * 10);
        skeletonLeftUpperPart[3] = sheet.crop(x, y * 10);
        skeletonLeftLowerPart[0] = sheet.crop(0, y * 11);
        skeletonLeftLowerPart[1] = sheet.crop(x, y * 11);
        skeletonLeftLowerPart[2] = sheet.crop(x * 2, y * 11);
        skeletonLeftLowerPart[3] = sheet.crop(x, y * 11);

        skeletonRightDeathAnimation[0][0] = sheet.crop(x, y * 8);
        skeletonRightDeathAnimation[0][1] = sheet.crop(x * 3, y * 8);
        skeletonRightDeathAnimation[0][2] = sheet.crop(x * 4, y * 8);
        skeletonRightDeathAnimation[0][3] = sheet.crop(x * 5, y * 8);
        skeletonRightDeathAnimation[0][4] = sheet.crop(x * 6, y * 8);
        skeletonRightDeathAnimation[0][5] = sheet.crop(x * 7, y * 8);  //
        skeletonRightDeathAnimation[1][0] = sheet.crop(x, y * 9);
        skeletonRightDeathAnimation[1][1] = sheet.crop(x * 3, y * 9);
        skeletonRightDeathAnimation[1][2] = sheet.crop(x * 4, y * 9);
        skeletonRightDeathAnimation[1][3] = sheet.crop(x * 5, y * 9);
        skeletonRightDeathAnimation[1][4] = sheet.crop(x * 6, y * 9);
        skeletonRightDeathAnimation[1][5] = sheet.crop(x * 7, y * 9);  //

        skeletonLeftDeathAnimation[0][0] = sheet.crop(x, y * 10);
        skeletonLeftDeathAnimation[0][1] = sheet.crop(x * 3, y * 10);
        skeletonLeftDeathAnimation[0][2] = sheet.crop(x * 4, y * 10);
        skeletonLeftDeathAnimation[0][3] = sheet.crop(x * 5, y * 10);
        skeletonLeftDeathAnimation[0][4] = sheet.crop(x * 6, y * 10);
        skeletonLeftDeathAnimation[0][5] = sheet.crop(x * 7, y * 10); //
        skeletonLeftDeathAnimation[1][0] = sheet.crop(x, y * 11);
        skeletonLeftDeathAnimation[1][1] = sheet.crop(x * 3, y * 11);
        skeletonLeftDeathAnimation[1][2] = sheet.crop(x * 4, y * 11);
        skeletonLeftDeathAnimation[1][3] = sheet.crop(x * 5, y * 11);
        skeletonLeftDeathAnimation[1][4] = sheet.crop(x * 6, y * 11);
        skeletonLeftDeathAnimation[1][5] = sheet.crop(x * 7, y * 11); //

        skeletonRightAttackAnimation[0][0] = sheet.crop(0, y * 12);
        skeletonRightAttackAnimation[0][1] = sheet.crop(0, y * 13);
        skeletonRightAttackAnimation[1][0] = sheet.crop(x, y * 12);
        skeletonRightAttackAnimation[1][1] = sheet.crop(x, y * 13);
        skeletonRightAttackAnimation[2][0] = sheet.crop(x * 2, y * 12);
        skeletonRightAttackAnimation[2][1] = sheet.crop(x * 2, y * 13);
        skeletonRightAttackAnimation[3][0] = sheet.crop(x * 3, y * 12);
        skeletonRightAttackAnimation[3][1] = sheet.crop(x * 3, y * 13);

        skeletonLeftAttackAnimation[0][0] = sheet.crop(x * 7, y * 12);
        skeletonLeftAttackAnimation[0][1] = sheet.crop(x * 7, y * 13);
        skeletonLeftAttackAnimation[1][0] = sheet.crop(x * 6, y * 12);
        skeletonLeftAttackAnimation[1][1] = sheet.crop(x * 6, y * 13);
        skeletonLeftAttackAnimation[2][0] = sheet.crop(x * 5, y * 12);
        skeletonLeftAttackAnimation[2][1] = sheet.crop(x * 5, y * 13);
        skeletonLeftAttackAnimation[3][0] = sheet.crop(x * 4, y * 12);
        skeletonLeftAttackAnimation[3][1] = sheet.crop(x * 4, y * 13);

        goblinRight[0][0] = sheet.crop(x * 18, y * 14);
        goblinRight[0][1] = sheet.crop(x * 18, y * 15);
        goblinRight[1][0] = sheet.crop(x * 19, y * 14);
        goblinRight[1][1] = sheet.crop(x * 19, y * 15);
        goblinRight[2][0] = sheet.crop(x * 20, y * 14);
        goblinRight[2][1] = sheet.crop(x * 20, y * 15);
        goblinRight[3][0] = sheet.crop(x * 19, y * 14);
        goblinRight[3][1] = sheet.crop(x * 19, y * 15);

        goblinAttackRight[0][0] = sheet.crop(x * 21, y * 14);
        goblinAttackRight[0][1] = sheet.crop(x * 21, y * 15);
        goblinAttackRight[1][0] = sheet.crop(x * 22, y * 14);
        goblinAttackRight[1][1] = sheet.crop(x * 22, y * 15);
        goblinAttackRight[2][0] = sheet.crop(x * 23, y * 14);
        goblinAttackRight[2][1] = sheet.crop(x * 23, y * 15);
        goblinAttackRight[3][0] = sheet.crop(x * 24, y * 14);
        goblinAttackRight[3][1] = sheet.crop(x * 24, y * 15);
        goblinAttackRight[4][0] = sheet.crop(x * 25, y * 14);
        goblinAttackRight[4][1] = sheet.crop(x * 25, y * 15);
        goblinAttackRight[5][0] = sheet.crop(x * 26, y * 14);
        goblinAttackRight[5][1] = sheet.crop(x * 26, y * 15);
        goblinAttackRight[6][0] = sheet.crop(x * 27, y * 14);
        goblinAttackRight[6][1] = sheet.crop(x * 27, y * 15);

        goblinDeathRight[0][0] = sheet.crop(x * 28, y * 14);
        goblinDeathRight[0][1] = sheet.crop(x * 28, y * 15);
        goblinDeathRight[1][0] = sheet.crop(x * 29, y * 14);
        goblinDeathRight[1][1] = sheet.crop(x * 29, y * 15);
        goblinDeathRight[2][0] = sheet.crop(x * 30, y * 14);
        goblinDeathRight[2][1] = sheet.crop(x * 30, y * 15);
        goblinDeathRight[3][0] = sheet.crop(x * 31, y * 14);
        goblinDeathRight[3][1] = sheet.crop(x * 31, y * 15);
        goblinDeathRight[4][0] = sheet.crop(x * 32, y * 14);
        goblinDeathRight[4][1] = sheet.crop(x * 32, y * 15);
        goblinDeathRight[5][0] = sheet.crop(x * 33, y * 14);
        goblinDeathRight[5][1] = sheet.crop(x * 33, y * 15);
        goblinDeathRight[6][0] = sheet.crop(x * 34, y * 14);
        goblinDeathRight[6][1] = sheet.crop(x * 34, y * 15);

        goblinLeft[0][0] = sheet.crop(x * 34, y * 16);
        goblinLeft[0][1] = sheet.crop(x * 34, y * 17);
        goblinLeft[1][0] = sheet.crop(x * 33, y * 16);
        goblinLeft[1][1] = sheet.crop(x * 33, y * 17);
        goblinLeft[2][0] = sheet.crop(x * 32, y * 16);
        goblinLeft[2][1] = sheet.crop(x * 32, y * 17);
        goblinLeft[3][0] = sheet.crop(x * 33, y * 16);
        goblinLeft[3][1] = sheet.crop(x * 33, y * 17);

        goblinAttackLeft[0][0] = sheet.crop(x * 31, y * 16);
        goblinAttackLeft[0][1] = sheet.crop(x * 31, y * 17);
        goblinAttackLeft[1][0] = sheet.crop(x * 30, y * 16);
        goblinAttackLeft[1][1] = sheet.crop(x * 30, y * 17);
        goblinAttackLeft[2][0] = sheet.crop(x * 29, y * 16);
        goblinAttackLeft[2][1] = sheet.crop(x * 29, y * 17);
        goblinAttackLeft[3][0] = sheet.crop(x * 28, y * 16);
        goblinAttackLeft[3][1] = sheet.crop(x * 28, y * 17);
        goblinAttackLeft[4][0] = sheet.crop(x * 27, y * 16);
        goblinAttackLeft[4][1] = sheet.crop(x * 27, y * 17);
        goblinAttackLeft[5][0] = sheet.crop(x * 26, y * 16);
        goblinAttackLeft[5][1] = sheet.crop(x * 26, y * 17);
        goblinAttackLeft[6][0] = sheet.crop(x * 25, y * 16);
        goblinAttackLeft[6][1] = sheet.crop(x * 25, y * 17);

        goblinDeathLeft[0][0] = sheet.crop(x * 24, y * 16);
        goblinDeathLeft[0][1] = sheet.crop(x * 24, y * 17);
        goblinDeathLeft[1][0] = sheet.crop(x * 23, y * 16);
        goblinDeathLeft[1][1] = sheet.crop(x * 23, y * 17);
        goblinDeathLeft[2][0] = sheet.crop(x * 22, y * 16);
        goblinDeathLeft[2][1] = sheet.crop(x * 22, y * 17);
        goblinDeathLeft[3][0] = sheet.crop(x * 21, y * 16);
        goblinDeathLeft[3][1] = sheet.crop(x * 21, y * 17);
        goblinDeathLeft[4][0] = sheet.crop(x * 20, y * 16);
        goblinDeathLeft[4][1] = sheet.crop(x * 20, y * 17);
        goblinDeathLeft[5][0] = sheet.crop(x * 19, y * 16);
        goblinDeathLeft[5][1] = sheet.crop(x * 19, y * 17);
        goblinDeathLeft[6][0] = sheet.crop(x * 18, y * 16);
        goblinDeathLeft[6][1] = sheet.crop(x * 18, y * 17);

        bullRight[0][0] = sheet.crop(0, y * 14);
        bullRight[0][1] = sheet.crop(x, y * 14);
        bullRight[0][2] = sheet.crop(x * 2, y * 14);
        bullRight[0][3] = sheet.crop(0, y * 15);
        bullRight[0][4] = sheet.crop(x, y * 15);
        bullRight[0][5] = sheet.crop(x * 2, y * 15);
        bullRight[0][6] = sheet.crop(0, y * 16);
        bullRight[0][7] = sheet.crop(x, y * 16);
        bullRight[0][8] = sheet.crop(x * 2, y * 16);

        bullRight[1][0] = sheet.crop(x * 3, y * 14);
        bullRight[1][1] = sheet.crop(x * 4, y * 14);
        bullRight[1][2] = sheet.crop(x * 5, y * 14);
        bullRight[1][3] = sheet.crop(x * 3, y * 15);
        bullRight[1][4] = sheet.crop(x * 4, y * 15);
        bullRight[1][5] = sheet.crop(x * 5, y * 15);
        bullRight[1][6] = sheet.crop(x * 3, y * 16);
        bullRight[1][7] = sheet.crop(x * 4, y * 16);
        bullRight[1][8] = sheet.crop(x * 5, y * 16);

        bullRight[2][0] = sheet.crop(x * 6, y * 14);
        bullRight[2][1] = sheet.crop(x * 7, y * 14);
        bullRight[2][2] = sheet.crop(x * 8, y * 14);
        bullRight[2][3] = sheet.crop(x * 6, y * 15);
        bullRight[2][4] = sheet.crop(x * 7, y * 15);
        bullRight[2][5] = sheet.crop(x * 8, y * 15);
        bullRight[2][6] = sheet.crop(x * 6, y * 16);
        bullRight[2][7] = sheet.crop(x * 7, y * 16);
        bullRight[2][8] = sheet.crop(x * 8, y * 16);

        bullRight[3][0] = sheet.crop(x * 9, y * 14);
        bullRight[3][1] = sheet.crop(x * 10, y * 14);
        bullRight[3][2] = sheet.crop(x * 11, y * 14);
        bullRight[3][3] = sheet.crop(x * 9, y * 15);
        bullRight[3][4] = sheet.crop(x * 10, y * 15);
        bullRight[3][5] = sheet.crop(x * 11, y * 15);
        bullRight[3][6] = sheet.crop(x * 9, y * 16);
        bullRight[3][7] = sheet.crop(x * 10, y * 16);
        bullRight[3][8] = sheet.crop(x * 11, y * 16);

        bullRight[4][0] = sheet.crop(x * 12, y * 14);
        bullRight[4][1] = sheet.crop(x * 13, y * 14);
        bullRight[4][2] = sheet.crop(x * 14, y * 14);
        bullRight[4][3] = sheet.crop(x * 12, y * 15);
        bullRight[4][4] = sheet.crop(x * 13, y * 15);
        bullRight[4][5] = sheet.crop(x * 14, y * 15);
        bullRight[4][6] = sheet.crop(x * 12, y * 16);
        bullRight[4][7] = sheet.crop(x * 13, y * 16);
        bullRight[4][8] = sheet.crop(x * 14, y * 16);

        bullRight[5][0] = sheet.crop(x * 15, y * 14);
        bullRight[5][1] = sheet.crop(x * 16, y * 14);
        bullRight[5][2] = sheet.crop(x * 17, y * 14);
        bullRight[5][3] = sheet.crop(x * 15, y * 15);
        bullRight[5][4] = sheet.crop(x * 16, y * 15);
        bullRight[5][5] = sheet.crop(x * 17, y * 15);
        bullRight[5][6] = sheet.crop(x * 15, y * 16);
        bullRight[5][7] = sheet.crop(x * 16, y * 16);
        bullRight[5][8] = sheet.crop(x * 17, y * 16);

        bullLeft[0][0] = sheet.crop(0, y * 17);
        bullLeft[0][1] = sheet.crop(x, y * 17);
        bullLeft[0][2] = sheet.crop(x * 2, y * 17);
        bullLeft[0][3] = sheet.crop(0, y * 18);
        bullLeft[0][4] = sheet.crop(x, y * 18);
        bullLeft[0][5] = sheet.crop(x * 2, y * 18);
        bullLeft[0][6] = sheet.crop(0, y * 19);
        bullLeft[0][7] = sheet.crop(x, y * 19);
        bullLeft[0][8] = sheet.crop(x * 2, y * 19);

        bullLeft[1][0] = sheet.crop(x * 3, y * 17);
        bullLeft[1][1] = sheet.crop(x * 4, y * 17);
        bullLeft[1][2] = sheet.crop(x * 5, y * 17);
        bullLeft[1][3] = sheet.crop(x * 3, y * 18);
        bullLeft[1][4] = sheet.crop(x * 4, y * 18);
        bullLeft[1][5] = sheet.crop(x * 5, y * 18);
        bullLeft[1][6] = sheet.crop(x * 3, y * 19);
        bullLeft[1][7] = sheet.crop(x * 4, y * 19);
        bullLeft[1][8] = sheet.crop(x * 5, y * 19);

        bullLeft[2][0] = sheet.crop(x * 6, y * 17);
        bullLeft[2][1] = sheet.crop(x * 7, y * 17);
        bullLeft[2][2] = sheet.crop(x * 8, y * 17);
        bullLeft[2][3] = sheet.crop(x * 6, y * 18);
        bullLeft[2][4] = sheet.crop(x * 7, y * 18);
        bullLeft[2][5] = sheet.crop(x * 8, y * 18);
        bullLeft[2][6] = sheet.crop(x * 6, y * 19);
        bullLeft[2][7] = sheet.crop(x * 7, y * 19);
        bullLeft[2][8] = sheet.crop(x * 8, y * 19);

        bullLeft[3][0] = sheet.crop(x * 9, y * 17);
        bullLeft[3][1] = sheet.crop(x * 10, y * 17);
        bullLeft[3][2] = sheet.crop(x * 11, y * 17);
        bullLeft[3][3] = sheet.crop(x * 9, y * 18);
        bullLeft[3][4] = sheet.crop(x * 10, y * 18);
        bullLeft[3][5] = sheet.crop(x * 11, y * 18);
        bullLeft[3][6] = sheet.crop(x * 9, y * 19);
        bullLeft[3][7] = sheet.crop(x * 10, y * 19);
        bullLeft[3][8] = sheet.crop(x * 11, y * 19);

        bullLeft[4][0] = sheet.crop(x * 12, y * 17);
        bullLeft[4][1] = sheet.crop(x * 13, y * 17);
        bullLeft[4][2] = sheet.crop(x * 14, y * 17);
        bullLeft[4][3] = sheet.crop(x * 12, y * 18);
        bullLeft[4][4] = sheet.crop(x * 13, y * 18);
        bullLeft[4][5] = sheet.crop(x * 14, y * 18);
        bullLeft[4][6] = sheet.crop(x * 12, y * 19);
        bullLeft[4][7] = sheet.crop(x * 13, y * 19);
        bullLeft[4][8] = sheet.crop(x * 14, y * 19);

        bullLeft[5][0] = sheet.crop(x * 15, y * 17);
        bullLeft[5][1] = sheet.crop(x * 16, y * 17);
        bullLeft[5][2] = sheet.crop(x * 17, y * 17);
        bullLeft[5][3] = sheet.crop(x * 15, y * 18);
        bullLeft[5][4] = sheet.crop(x * 16, y * 18);
        bullLeft[5][5] = sheet.crop(x * 17, y * 18);
        bullLeft[5][6] = sheet.crop(x * 15, y * 19);
        bullLeft[5][7] = sheet.crop(x * 16, y * 19);
        bullLeft[5][8] = sheet.crop(x * 17, y * 19);

        bullTurning[0][0] = sheet.crop(0, y * 14);
        bullTurning[0][1] = sheet.crop(x, y * 14);
        bullTurning[0][2] = sheet.crop(x * 2, y * 14);
        bullTurning[0][3] = sheet.crop(0, y * 15);
        bullTurning[0][4] = sheet.crop(x, y * 15);
        bullTurning[0][5] = sheet.crop(x * 2, y * 15);
        bullTurning[0][6] = sheet.crop(0, y * 16);
        bullTurning[0][7] = sheet.crop(x, y * 16);
        bullTurning[0][8] = sheet.crop(x * 2, y * 16);

        bullTurning[1][0] = sheet.crop(0, y * 20);
        bullTurning[1][1] = sheet.crop(x, y * 20);
        bullTurning[1][2] = sheet.crop(x * 2, y * 20);
        bullTurning[1][3] = sheet.crop(0, y * 21);
        bullTurning[1][4] = sheet.crop(x, y * 21);
        bullTurning[1][5] = sheet.crop(x * 2, y * 21);
        bullTurning[1][6] = sheet.crop(0, y * 22);
        bullTurning[1][7] = sheet.crop(x, y * 22);
        bullTurning[1][8] = sheet.crop(x * 2, y * 22);

        bullTurning[2][0] = sheet.crop(x * 3, y * 20);
        bullTurning[2][1] = sheet.crop(x * 4, y * 20);
        bullTurning[2][2] = sheet.crop(x * 5, y * 20);
        bullTurning[2][3] = sheet.crop(x * 3, y * 21);
        bullTurning[2][4] = sheet.crop(x * 4, y * 21);
        bullTurning[2][5] = sheet.crop(x * 5, y * 21);
        bullTurning[2][6] = sheet.crop(x * 3, y * 22);
        bullTurning[2][7] = sheet.crop(x * 4, y * 22);
        bullTurning[2][8] = sheet.crop(x * 5, y * 22);

        bullTurning[3][0] = sheet.crop(x * 6, y * 20);
        bullTurning[3][1] = sheet.crop(x * 7, y * 20);
        bullTurning[3][2] = sheet.crop(x * 8, y * 20);
        bullTurning[3][3] = sheet.crop(x * 6, y * 21);
        bullTurning[3][4] = sheet.crop(x * 7, y * 21);
        bullTurning[3][5] = sheet.crop(x * 8, y * 21);
        bullTurning[3][6] = sheet.crop(x * 6, y * 22);
        bullTurning[3][7] = sheet.crop(x * 7, y * 22);
        bullTurning[3][8] = sheet.crop(x * 8, y * 22);

        bullTurning[4][0] = sheet.crop(x * 9, y * 20);
        bullTurning[4][1] = sheet.crop(x * 10, y * 20);
        bullTurning[4][2] = sheet.crop(x * 11, y * 20);
        bullTurning[4][3] = sheet.crop(x * 9, y * 21);
        bullTurning[4][4] = sheet.crop(x * 10, y * 21);
        bullTurning[4][5] = sheet.crop(x * 11, y * 21);
        bullTurning[4][6] = sheet.crop(x * 9, y * 22);
        bullTurning[4][7] = sheet.crop(x * 10, y * 22);
        bullTurning[4][8] = sheet.crop(x * 11, y * 22);

        bullTurning[5][0] = sheet.crop(x * 12, y * 20);
        bullTurning[5][1] = sheet.crop(x * 13, y * 20);
        bullTurning[5][2] = sheet.crop(x * 14, y * 20);
        bullTurning[5][3] = sheet.crop(x * 12, y * 21);
        bullTurning[5][4] = sheet.crop(x * 13, y * 21);
        bullTurning[5][5] = sheet.crop(x * 14, y * 21);
        bullTurning[5][6] = sheet.crop(x * 12, y * 22);
        bullTurning[5][7] = sheet.crop(x * 13, y * 22);
        bullTurning[5][8] = sheet.crop(x * 14, y * 22);

        bullTurning[6][0] = sheet.crop(x * 15, y * 20);
        bullTurning[6][1] = sheet.crop(x * 16, y * 20);
        bullTurning[6][2] = sheet.crop(x * 17, y * 20);
        bullTurning[6][3] = sheet.crop(x * 15, y * 21);
        bullTurning[6][4] = sheet.crop(x * 16, y * 21);
        bullTurning[6][5] = sheet.crop(x * 17, y * 21);
        bullTurning[6][6] = sheet.crop(x * 15, y * 22);
        bullTurning[6][7] = sheet.crop(x * 16, y * 22);
        bullTurning[6][8] = sheet.crop(x * 17, y * 22);

        bullTurning[7][0] = sheet.crop(x * 15, y * 17);
        bullTurning[7][1] = sheet.crop(x * 16, y * 17);
        bullTurning[7][2] = sheet.crop(x * 17, y * 17);
        bullTurning[7][3] = sheet.crop(x * 15, y * 18);
        bullTurning[7][4] = sheet.crop(x * 16, y * 18);
        bullTurning[7][5] = sheet.crop(x * 17, y * 18);
        bullTurning[7][6] = sheet.crop(x * 15, y * 19);
        bullTurning[7][7] = sheet.crop(x * 16, y * 19);
        bullTurning[7][8] = sheet.crop(x * 17, y * 19);

        bullAttackRight[0][0] = sheet.crop(0, y * 23);
        bullAttackRight[0][1] = sheet.crop(x, y * 23);
        bullAttackRight[0][2] = sheet.crop(x * 2, y * 23);
        bullAttackRight[0][3] = sheet.crop(0, y * 24);
        bullAttackRight[0][4] = sheet.crop(x, y * 24);
        bullAttackRight[0][5] = sheet.crop(x * 2, y * 24);
        bullAttackRight[0][6] = sheet.crop(0, y * 25);
        bullAttackRight[0][7] = sheet.crop(x, y * 25);
        bullAttackRight[0][8] = sheet.crop(x * 2, y * 25);

        bullAttackRight[1][0] = sheet.crop(x * 3, y * 23);
        bullAttackRight[1][1] = sheet.crop(x * 4, y * 23);
        bullAttackRight[1][2] = sheet.crop(x * 5, y * 23);
        bullAttackRight[1][3] = sheet.crop(x * 3, y * 24);
        bullAttackRight[1][4] = sheet.crop(x * 4, y * 24);
        bullAttackRight[1][5] = sheet.crop(x * 5, y * 24);
        bullAttackRight[1][6] = sheet.crop(x * 3, y * 25);
        bullAttackRight[1][7] = sheet.crop(x * 4, y * 25);
        bullAttackRight[1][8] = sheet.crop(x * 5, y * 25);

        bullAttackRight[2][0] = sheet.crop(x * 6, y * 23);
        bullAttackRight[2][1] = sheet.crop(x * 7, y * 23);
        bullAttackRight[2][2] = sheet.crop(x * 8, y * 23);
        bullAttackRight[2][3] = sheet.crop(x * 6, y * 24);
        bullAttackRight[2][4] = sheet.crop(x * 7, y * 24);
        bullAttackRight[2][5] = sheet.crop(x * 8, y * 24);
        bullAttackRight[2][6] = sheet.crop(x * 6, y * 25);
        bullAttackRight[2][7] = sheet.crop(x * 7, y * 25);
        bullAttackRight[2][8] = sheet.crop(x * 8, y * 25);

        bullAttackRight[3][0] = sheet.crop(x * 9, y * 23);
        bullAttackRight[3][1] = sheet.crop(x * 10, y * 23);
        bullAttackRight[3][2] = sheet.crop(x * 11, y * 23);
        bullAttackRight[3][3] = sheet.crop(x * 9, y * 24);
        bullAttackRight[3][4] = sheet.crop(x * 10, y * 24);
        bullAttackRight[3][5] = sheet.crop(x * 11, y * 24);
        bullAttackRight[3][6] = sheet.crop(x * 9, y * 25);
        bullAttackRight[3][7] = sheet.crop(x * 10, y * 25);
        bullAttackRight[3][8] = sheet.crop(x * 11, y * 25);

        bullAttackRight[4][0] = sheet.crop(x * 12, y * 23);
        bullAttackRight[4][1] = sheet.crop(x * 13, y * 23);
        bullAttackRight[4][2] = sheet.crop(x * 14, y * 23);
        bullAttackRight[4][3] = sheet.crop(x * 12, y * 24);
        bullAttackRight[4][4] = sheet.crop(x * 13, y * 24);
        bullAttackRight[4][5] = sheet.crop(x * 14, y * 24);
        bullAttackRight[4][6] = sheet.crop(x * 12, y * 25);
        bullAttackRight[4][7] = sheet.crop(x * 13, y * 25);
        bullAttackRight[4][8] = sheet.crop(x * 14, y * 25);

        bullAttackRight[5][0] = sheet.crop(x * 15, y * 23);
        bullAttackRight[5][1] = sheet.crop(x * 16, y * 23);
        bullAttackRight[5][2] = sheet.crop(x * 17, y * 23);
        bullAttackRight[5][3] = sheet.crop(x * 15, y * 24);
        bullAttackRight[5][4] = sheet.crop(x * 16, y * 24);
        bullAttackRight[5][5] = sheet.crop(x * 17, y * 24);
        bullAttackRight[5][6] = sheet.crop(x * 15, y * 25);
        bullAttackRight[5][7] = sheet.crop(x * 16, y * 25);
        bullAttackRight[5][8] = sheet.crop(x * 17, y * 25);

        bullAttackLeft[0][0] = sheet.crop(0, y * 26);
        bullAttackLeft[0][1] = sheet.crop(x, y * 26);
        bullAttackLeft[0][2] = sheet.crop(x * 2, y * 26);
        bullAttackLeft[0][3] = sheet.crop(0, y * 27);
        bullAttackLeft[0][4] = sheet.crop(x, y * 27);
        bullAttackLeft[0][5] = sheet.crop(x * 2, y * 27);
        bullAttackLeft[0][6] = sheet.crop(0, y * 28);
        bullAttackLeft[0][7] = sheet.crop(x, y * 28);
        bullAttackLeft[0][8] = sheet.crop(x * 2, y * 28);

        bullAttackLeft[1][0] = sheet.crop(x * 3, y * 26);
        bullAttackLeft[1][1] = sheet.crop(x * 4, y * 26);
        bullAttackLeft[1][2] = sheet.crop(x * 5, y * 26);
        bullAttackLeft[1][3] = sheet.crop(x * 3, y * 27);
        bullAttackLeft[1][4] = sheet.crop(x * 4, y * 27);
        bullAttackLeft[1][5] = sheet.crop(x * 5, y * 27);
        bullAttackLeft[1][6] = sheet.crop(x * 3, y * 28);
        bullAttackLeft[1][7] = sheet.crop(x * 4, y * 28);
        bullAttackLeft[1][8] = sheet.crop(x * 5, y * 28);

        bullAttackLeft[2][0] = sheet.crop(x * 6, y * 26);
        bullAttackLeft[2][1] = sheet.crop(x * 7, y * 26);
        bullAttackLeft[2][2] = sheet.crop(x * 8, y * 26);
        bullAttackLeft[2][3] = sheet.crop(x * 6, y * 27);
        bullAttackLeft[2][4] = sheet.crop(x * 7, y * 27);
        bullAttackLeft[2][5] = sheet.crop(x * 8, y * 27);
        bullAttackLeft[2][6] = sheet.crop(x * 6, y * 28);
        bullAttackLeft[2][7] = sheet.crop(x * 7, y * 28);
        bullAttackLeft[2][8] = sheet.crop(x * 8, y * 28);

        bullAttackLeft[3][0] = sheet.crop(x * 9, y * 26);
        bullAttackLeft[3][1] = sheet.crop(x * 10, y * 26);
        bullAttackLeft[3][2] = sheet.crop(x * 11, y * 26);
        bullAttackLeft[3][3] = sheet.crop(x * 9, y * 27);
        bullAttackLeft[3][4] = sheet.crop(x * 10, y * 27);
        bullAttackLeft[3][5] = sheet.crop(x * 11, y * 27);
        bullAttackLeft[3][6] = sheet.crop(x * 9, y * 28);
        bullAttackLeft[3][7] = sheet.crop(x * 10, y * 28);
        bullAttackLeft[3][8] = sheet.crop(x * 11, y * 28);

        bullAttackLeft[4][0] = sheet.crop(x * 12, y * 26);
        bullAttackLeft[4][1] = sheet.crop(x * 13, y * 26);
        bullAttackLeft[4][2] = sheet.crop(x * 14, y * 26);
        bullAttackLeft[4][3] = sheet.crop(x * 12, y * 27);
        bullAttackLeft[4][4] = sheet.crop(x * 13, y * 27);
        bullAttackLeft[4][5] = sheet.crop(x * 14, y * 27);
        bullAttackLeft[4][6] = sheet.crop(x * 12, y * 28);
        bullAttackLeft[4][7] = sheet.crop(x * 13, y * 28);
        bullAttackLeft[4][8] = sheet.crop(x * 14, y * 28);

        bullAttackLeft[5][0] = sheet.crop(x * 15, y * 26);
        bullAttackLeft[5][1] = sheet.crop(x * 16, y * 26);
        bullAttackLeft[5][2] = sheet.crop(x * 17, y * 26);
        bullAttackLeft[5][3] = sheet.crop(x * 15, y * 27);
        bullAttackLeft[5][4] = sheet.crop(x * 16, y * 27);
        bullAttackLeft[5][5] = sheet.crop(x * 17, y * 27);
        bullAttackLeft[5][6] = sheet.crop(x * 15, y * 28);
        bullAttackLeft[5][7] = sheet.crop(x * 16, y * 28);
        bullAttackLeft[5][8] = sheet.crop(x * 17, y * 28);

        bullDeathRight[0][0] = sheet.crop(0, y * 14);
        bullDeathRight[0][1] = sheet.crop(x, y * 14);
        bullDeathRight[0][2] = sheet.crop(x * 2, y * 14);
        bullDeathRight[0][3] = sheet.crop(0, y * 15);
        bullDeathRight[0][4] = sheet.crop(x, y * 15);
        bullDeathRight[0][5] = sheet.crop(x * 2, y * 15);
        bullDeathRight[0][6] = sheet.crop(0, y * 16);
        bullDeathRight[0][7] = sheet.crop(x, y * 16);
        bullDeathRight[0][8] = sheet.crop(x * 2, y * 16);

        bullDeathRight[1][0] = sheet.crop(x * 3, y * 29);
        bullDeathRight[1][1] = sheet.crop(x * 4, y * 29);
        bullDeathRight[1][2] = sheet.crop(x * 5, y * 29);
        bullDeathRight[1][3] = sheet.crop(x * 3, y * 30);
        bullDeathRight[1][4] = sheet.crop(x * 4, y * 30);
        bullDeathRight[1][5] = sheet.crop(x * 5, y * 30);
        bullDeathRight[1][6] = sheet.crop(x * 3, y * 31);
        bullDeathRight[1][7] = sheet.crop(x * 4, y * 31);
        bullDeathRight[1][8] = sheet.crop(x * 5, y * 31);

        bullDeathRight[2][0] = sheet.crop(x * 6, y * 29);
        bullDeathRight[2][1] = sheet.crop(x * 7, y * 29);
        bullDeathRight[2][2] = sheet.crop(x * 8, y * 29);
        bullDeathRight[2][3] = sheet.crop(x * 6, y * 30);
        bullDeathRight[2][4] = sheet.crop(x * 7, y * 30);
        bullDeathRight[2][5] = sheet.crop(x * 8, y * 30);
        bullDeathRight[2][6] = sheet.crop(x * 6, y * 31);
        bullDeathRight[2][7] = sheet.crop(x * 7, y * 31);
        bullDeathRight[2][8] = sheet.crop(x * 8, y * 31);

        bullDeathRight[3][0] = sheet.crop(x * 9, y * 29);
        bullDeathRight[3][1] = sheet.crop(x * 10, y * 29);
        bullDeathRight[3][2] = sheet.crop(x * 11, y * 29);
        bullDeathRight[3][3] = sheet.crop(x * 9, y * 30);
        bullDeathRight[3][4] = sheet.crop(x * 10, y * 30);
        bullDeathRight[3][5] = sheet.crop(x * 11, y * 30);
        bullDeathRight[3][6] = sheet.crop(x * 9, y * 31);
        bullDeathRight[3][7] = sheet.crop(x * 10, y * 31);
        bullDeathRight[3][8] = sheet.crop(x * 11, y * 31);

        bullDeathRight[4][0] = sheet.crop(x * 12, y * 29);
        bullDeathRight[4][1] = sheet.crop(x * 13, y * 29);
        bullDeathRight[4][2] = sheet.crop(x * 14, y * 29);
        bullDeathRight[4][3] = sheet.crop(x * 12, y * 30);
        bullDeathRight[4][4] = sheet.crop(x * 13, y * 30);
        bullDeathRight[4][5] = sheet.crop(x * 14, y * 30);
        bullDeathRight[4][6] = sheet.crop(x * 12, y * 31);
        bullDeathRight[4][7] = sheet.crop(x * 13, y * 31);
        bullDeathRight[4][8] = sheet.crop(x * 14, y * 31);

        bullDeathRight[5][0] = sheet.crop(x * 15, y * 29);
        bullDeathRight[5][1] = sheet.crop(x * 16, y * 29);
        bullDeathRight[5][2] = sheet.crop(x * 17, y * 29);
        bullDeathRight[5][3] = sheet.crop(x * 15, y * 30);
        bullDeathRight[5][4] = sheet.crop(x * 16, y * 30);
        bullDeathRight[5][5] = sheet.crop(x * 17, y * 30);
        bullDeathRight[5][6] = sheet.crop(x * 15, y * 31);
        bullDeathRight[5][7] = sheet.crop(x * 16, y * 31);
        bullDeathRight[5][8] = sheet.crop(x * 17, y * 31);

        bullDeathLeft[0][0] = sheet.crop(0, y * 17);
        bullDeathLeft[0][1] = sheet.crop(x, y * 17);
        bullDeathLeft[0][2] = sheet.crop(x * 2, y * 17);
        bullDeathLeft[0][3] = sheet.crop(0, y * 18);
        bullDeathLeft[0][4] = sheet.crop(x, y * 18);
        bullDeathLeft[0][5] = sheet.crop(x * 2, y * 18);
        bullDeathLeft[0][6] = sheet.crop(0, y * 19);
        bullDeathLeft[0][7] = sheet.crop(x, y * 19);
        bullDeathLeft[0][8] = sheet.crop(x * 2, y * 19);

        bullDeathLeft[1][0] = sheet.crop(x * 3, y * 32);
        bullDeathLeft[1][1] = sheet.crop(x * 4, y * 32);
        bullDeathLeft[1][2] = sheet.crop(x * 5, y * 32);
        bullDeathLeft[1][3] = sheet.crop(x * 3, y * 33);
        bullDeathLeft[1][4] = sheet.crop(x * 4, y * 33);
        bullDeathLeft[1][5] = sheet.crop(x * 5, y * 33);
        bullDeathLeft[1][6] = sheet.crop(x * 3, y * 34);
        bullDeathLeft[1][7] = sheet.crop(x * 4, y * 34);
        bullDeathLeft[1][8] = sheet.crop(x * 5, y * 34);

        bullDeathLeft[2][0] = sheet.crop(x * 6, y * 32);
        bullDeathLeft[2][1] = sheet.crop(x * 7, y * 32);
        bullDeathLeft[2][2] = sheet.crop(x * 8, y * 32);
        bullDeathLeft[2][3] = sheet.crop(x * 6, y * 33);
        bullDeathLeft[2][4] = sheet.crop(x * 7, y * 33);
        bullDeathLeft[2][5] = sheet.crop(x * 8, y * 33);
        bullDeathLeft[2][6] = sheet.crop(x * 6, y * 34);
        bullDeathLeft[2][7] = sheet.crop(x * 7, y * 34);
        bullDeathLeft[2][8] = sheet.crop(x * 8, y * 34);

        bullDeathLeft[3][0] = sheet.crop(x * 9, y * 32);
        bullDeathLeft[3][1] = sheet.crop(x * 10, y * 32);
        bullDeathLeft[3][2] = sheet.crop(x * 11, y * 32);
        bullDeathLeft[3][3] = sheet.crop(x * 9, y * 33);
        bullDeathLeft[3][4] = sheet.crop(x * 10, y * 33);
        bullDeathLeft[3][5] = sheet.crop(x * 11, y * 33);
        bullDeathLeft[3][6] = sheet.crop(x * 9, y * 34);
        bullDeathLeft[3][7] = sheet.crop(x * 10, y * 34);
        bullDeathLeft[3][8] = sheet.crop(x * 11, y * 34);

        bullDeathLeft[4][0] = sheet.crop(x * 12, y * 32);
        bullDeathLeft[4][1] = sheet.crop(x * 13, y * 32);
        bullDeathLeft[4][2] = sheet.crop(x * 14, y * 32);
        bullDeathLeft[4][3] = sheet.crop(x * 12, y * 33);
        bullDeathLeft[4][4] = sheet.crop(x * 13, y * 33);
        bullDeathLeft[4][5] = sheet.crop(x * 14, y * 33);
        bullDeathLeft[4][6] = sheet.crop(x * 12, y * 34);
        bullDeathLeft[4][7] = sheet.crop(x * 13, y * 34);
        bullDeathLeft[4][8] = sheet.crop(x * 14, y * 34);

        bullDeathLeft[5][0] = sheet.crop(x * 15, y * 32);
        bullDeathLeft[5][1] = sheet.crop(x * 16, y * 32);
        bullDeathLeft[5][2] = sheet.crop(x * 17, y * 32);
        bullDeathLeft[5][3] = sheet.crop(x * 15, y * 33);
        bullDeathLeft[5][4] = sheet.crop(x * 16, y * 33);
        bullDeathLeft[5][5] = sheet.crop(x * 17, y * 33);
        bullDeathLeft[5][6] = sheet.crop(x * 15, y * 34);
        bullDeathLeft[5][7] = sheet.crop(x * 16, y * 34);
        bullDeathLeft[5][8] = sheet.crop(x * 17, y * 34);

        dungeonDoor[0][0] = sheet.crop(28 * x, 0);
        dungeonDoor[0][1] = sheet.crop(29 * x, 0);
        dungeonDoor[0][2] = sheet.crop(28 * x, y);
        dungeonDoor[0][3] = sheet.crop(29 * x, y);

        dungeonDoor[1][0] = sheet.crop(28 * x, y * 2);
        dungeonDoor[1][1] = sheet.crop(29 * x, y * 2);
        dungeonDoor[1][2] = sheet.crop(28 * x, y * 3);
        dungeonDoor[1][3] = sheet.crop(29 * x, y * 3);

        dungeonDoor[2][0] = sheet.crop(28 * x, y * 4);
        dungeonDoor[2][1] = sheet.crop(29 * x, y * 4);
        dungeonDoor[2][2] = sheet.crop(28 * x, y * 5);
        dungeonDoor[2][3] = sheet.crop(29 * x, y * 5);

        dungeonDoor[3][0] = sheet.crop(28 * x, y * 6);
        dungeonDoor[3][1] = sheet.crop(29 * x, y * 6);
        dungeonDoor[3][2] = sheet.crop(28 * x, y * 7);
        dungeonDoor[3][3] = sheet.crop(29 * x, y * 7);

        dungeonDoor[4][0] = sheet.crop(28 * x, y * 8);
        dungeonDoor[4][1] = sheet.crop(29 * x, y * 8);
        dungeonDoor[4][2] = sheet.crop(28 * x, y * 9);
        dungeonDoor[4][3] = sheet.crop(29 * x, y * 9);

        dungeonDoor[5][0] = sheet.crop(28 * x, y * 10);
        dungeonDoor[5][1] = sheet.crop(29 * x, y * 10);
        dungeonDoor[5][2] = sheet.crop(28 * x, y * 11);
        dungeonDoor[5][3] = sheet.crop(29 * x, y * 11);

        dungeonDoor[6][0] = sheet.crop(28 * x, y * 12);
        dungeonDoor[6][1] = sheet.crop(29 * x, y * 12);
        dungeonDoor[6][2] = sheet.crop(28 * x, y * 13);
        dungeonDoor[6][3] = sheet.crop(29 * x, y * 13);

        trubkaZavrena[0] = sheet.crop(26 * x, 0);
        trubkaZavrena[1] = sheet.crop(27 * x, 0);
        trubkaZavrena[2] = sheet.crop(26 * x, y);
        trubkaZavrena[3] = sheet.crop(27 * x, y);

        trubkaSOcima[0] = sheet.crop(26 * x, y * 2);
        trubkaSOcima[1] = sheet.crop(27 * x, y * 2);
        trubkaSOcima[2] = sheet.crop(26 * x, y * 3);
        trubkaSOcima[3] = sheet.crop(27 * x, y * 3);

        trubkaOtevrena[0] = sheet.crop(26 * x, y * 4);
        trubkaOtevrena[1] = sheet.crop(27 * x, y * 4);
        trubkaOtevrena[2] = sheet.crop(26 * x, y * 5);
        trubkaOtevrena[3] = sheet.crop(27 * x, y * 5);


        playerMoveRight[0] = sheet.crop(x * 4, y * 4);
        playerMoveRight[1] = sheet.crop(x * 5, y * 4);

        playerMoveLeft[0] = sheet.crop(x * 4, y * 3);
        playerMoveLeft[1] = sheet.crop(x * 5, y * 3);

        playerRightIdle[0] = sheet.crop(x * 4, y * 4);
        playerRightIdle[1] = sheet.crop(x * 4, y * 4);
        playerRightIdle[2] = sheet.crop(x * 4, y * 4);
        playerRightIdle[3] = sheet.crop(x * 4, y * 4);
        playerRightIdle[4] = sheet.crop(x * 4, y * 4);
        playerRightIdle[5] = sheet.crop(x * 4, y * 4);
        playerRightIdle[6] = sheet.crop(x * 4, y * 4);
        playerRightIdle[7] = sheet.crop(x * 6, y * 4);

        playerleftIdle[0] = sheet.crop(x * 4, y * 3);
        playerleftIdle[1] = sheet.crop(x * 4, y * 3);
        playerleftIdle[2] = sheet.crop(x * 4, y * 3);
        playerleftIdle[3] = sheet.crop(x * 4, y * 3);
        playerleftIdle[4] = sheet.crop(x * 4, y * 3);
        playerleftIdle[5] = sheet.crop(x * 4, y * 3);
        playerleftIdle[6] = sheet.crop(x * 4, y * 3);
        playerleftIdle[7] = sheet.crop(x * 6, y * 3);

        btnStart[0] = sheet.crop(x * 24, y);
        btnStart[1] = sheet.crop(x * 25, y);

        btnScoreBoard[0] = sheet.crop(x * 24, y * 2);
        btnScoreBoard[1] = sheet.crop(x * 25, y * 2);

        btnBack[0] = sheet.crop(x * 24, y * 3);
        btnBack[1] = sheet.crop(x * 25, y * 3);

        btnQuit[0] = sheet.crop(x * 24, y * 4);
        btnQuit[1] = sheet.crop(x * 25, y * 4);

        btnSetings[0] = sheet.crop(x * 24, y * 5);
        btnSetings[1] = sheet.crop(x * 25, y * 5);

        btnRestart[0] = sheet.crop(x * 24, y * 6);
        btnRestart[1] = sheet.crop(x * 25, y * 6);

        btnMenu[0] = sheet.crop(x * 24, y * 7);
        btnMenu[1] = sheet.crop(x * 25, y * 7);

        btnResume[0] = sheet.crop(x * 24, y * 8);
        btnResume[1] = sheet.crop(x * 25, y * 8);

        btnBuy[0] = sheet.crop(x * 24, y * 9);
        btnBuy[1] = sheet.crop(x * 25, y * 9);

        btnCross[0] = sheet.crop(x * 24, y * 10);
        btnCross[1] = sheet.crop(x * 25, y * 10);

        btnClearData[0] = sheet.crop(x * 24, y * 11);
        btnClearData[1] = sheet.crop(x * 25, y * 11);


    }

    private static void cropDragon(SpriteSheet dragonTexturesSheet) {
        int x = 160; // 32 * 5
        int y = 128; // 32 * 4

        dragonMovingRight[0] = dragonTexturesSheet.crop(x, y);
        dragonMovingRight[1] = dragonTexturesSheet.crop(x * 2, y);
        dragonMovingRight[2] = dragonTexturesSheet.crop(x * 3, y);
        dragonMovingRight[3] = dragonTexturesSheet.crop(x * 4, y);
        dragonMovingRight[4] = dragonTexturesSheet.crop(x * 5, y);
        dragonMovingRight[5] = dragonTexturesSheet.crop(x * 6, y);
        dragonMovingRight[6] = dragonTexturesSheet.crop(x * 7, y);
        dragonMovingRight[7] = dragonTexturesSheet.crop(x * 8, y);

        dragonMovingLeft[0] = dragonTexturesSheet.crop(x * 8, 0);
        dragonMovingLeft[1] = dragonTexturesSheet.crop(x * 7, 0);
        dragonMovingLeft[2] = dragonTexturesSheet.crop(x * 6, 0);
        dragonMovingLeft[3] = dragonTexturesSheet.crop(x * 5, 0);
        dragonMovingLeft[4] = dragonTexturesSheet.crop(x * 4, 0);
        dragonMovingLeft[5] = dragonTexturesSheet.crop(x * 3, 0);
        dragonMovingLeft[6] = dragonTexturesSheet.crop(x * 2, 0);
        dragonMovingLeft[7] = dragonTexturesSheet.crop(x, 0);

        dragonAttackRight[0] = dragonTexturesSheet.crop(0, y * 3);
        dragonAttackRight[1] = dragonTexturesSheet.crop(x, y * 3);
        dragonAttackRight[2] = dragonTexturesSheet.crop(x * 2, y * 3);
        dragonAttackRight[3] = dragonTexturesSheet.crop(x * 3, y * 3);
        dragonAttackRight[4] = dragonTexturesSheet.crop(x * 4, y * 3);
        dragonAttackRight[5] = dragonTexturesSheet.crop(x * 5, y * 3);
        dragonAttackRight[6] = dragonTexturesSheet.crop(x * 6, y * 3);
        dragonAttackRight[7] = dragonTexturesSheet.crop(x * 7, y * 3);

        dragonAttackLeft[0] = dragonTexturesSheet.crop(x * 7, y * 4);
        dragonAttackLeft[1] = dragonTexturesSheet.crop(x * 6, y * 4);
        dragonAttackLeft[2] = dragonTexturesSheet.crop(x * 5, y * 4);
        dragonAttackLeft[3] = dragonTexturesSheet.crop(x * 4, y * 4);
        dragonAttackLeft[4] = dragonTexturesSheet.crop(x * 3, y * 4);
        dragonAttackLeft[5] = dragonTexturesSheet.crop(x * 2, y * 4);
        dragonAttackLeft[6] = dragonTexturesSheet.crop(x, y * 4);
        dragonAttackLeft[7] = dragonTexturesSheet.crop(0, y * 4);

        dragonTurningRight[0] = dragonTexturesSheet.crop(0, 0);
        dragonTurningRight[1] = dragonTexturesSheet.crop(x, y * 2);
        dragonTurningRight[2] = dragonTexturesSheet.crop(x, y * 2);
        dragonTurningRight[3] = dragonTexturesSheet.crop(x * 3, y * 2);
        dragonTurningRight[4] = dragonTexturesSheet.crop(x * 2, y * 2);
        dragonTurningRight[5] = dragonTexturesSheet.crop(x * 9, y);

        dragonTurningLeft[0] = dragonTexturesSheet.crop(0, y);
        dragonTurningLeft[1] = dragonTexturesSheet.crop(x * 2, y * 2);
        dragonTurningLeft[2] = dragonTexturesSheet.crop(x * 3, y * 2);
        dragonTurningLeft[3] = dragonTexturesSheet.crop(0, y * 2);
        dragonTurningLeft[4] = dragonTexturesSheet.crop(x, y * 2);
        dragonTurningLeft[5] = dragonTexturesSheet.crop(0, 0);

        dragonDeathRight[0] = dragonTexturesSheet.crop(0, y * 5);
        dragonDeathRight[1] = dragonTexturesSheet.crop(x, y * 5);
        dragonDeathRight[2] = dragonTexturesSheet.crop(x * 2, y * 5);
        dragonDeathRight[3] = dragonTexturesSheet.crop(x * 3, y * 5);
        dragonDeathRight[4] = dragonTexturesSheet.crop(x * 4, y * 5);
        dragonDeathRight[5] = dragonTexturesSheet.crop(x * 5, y * 5);
        dragonDeathRight[6] = dragonTexturesSheet.crop(x * 6, y * 5);
        dragonDeathRight[7] = dragonTexturesSheet.crop(x * 7, y * 5);
        dragonDeathRight[8] = dragonTexturesSheet.crop(x * 8, y * 5);
        dragonDeathRight[9] = dragonTexturesSheet.crop(x * 9, y * 5);
        dragonDeathRight[10] = dragonTexturesSheet.crop(0, y * 6);
        dragonDeathRight[11] = dragonTexturesSheet.crop(x, y * 6);
        dragonDeathRight[12] = dragonTexturesSheet.crop(x * 2, y * 6);
        dragonDeathRight[13] = dragonTexturesSheet.crop(x * 3, y * 6);
        dragonDeathRight[14] = dragonTexturesSheet.crop(x * 4, y * 6);
        dragonDeathRight[15] = dragonTexturesSheet.crop(x * 5, y * 6);
        dragonDeathRight[16] = dragonTexturesSheet.crop(x * 6, y * 6);
        dragonDeathRight[17] = dragonTexturesSheet.crop(x * 7, y * 6);
        dragonDeathRight[18] = dragonTexturesSheet.crop(x * 8, y * 6);
        dragonDeathRight[19] = dragonTexturesSheet.crop(x * 9, y * 6);
        dragonDeathRight[20] = dragonTexturesSheet.crop(0, y * 7);
        dragonDeathRight[21] = dragonTexturesSheet.crop(x, y * 7);
        dragonDeathRight[22] = dragonTexturesSheet.crop(x * 2, y * 7);

        dragonDeathLeft[0] = dragonTexturesSheet.crop(x * 9, y * 8);
        dragonDeathLeft[1] = dragonTexturesSheet.crop(x * 8, y * 8);
        dragonDeathLeft[2] = dragonTexturesSheet.crop(x * 7, y * 8);
        dragonDeathLeft[3] = dragonTexturesSheet.crop(x * 6, y * 8);
        dragonDeathLeft[4] = dragonTexturesSheet.crop(x * 5, y * 8);
        dragonDeathLeft[5] = dragonTexturesSheet.crop(x * 4, y * 8);
        dragonDeathLeft[6] = dragonTexturesSheet.crop(x * 3, y * 8);
        dragonDeathLeft[7] = dragonTexturesSheet.crop(x * 2, y * 8);
        dragonDeathLeft[8] = dragonTexturesSheet.crop(x, y * 8);
        dragonDeathLeft[9] = dragonTexturesSheet.crop(0, y * 8);
        dragonDeathLeft[10] = dragonTexturesSheet.crop(x * 9, y * 9);
        dragonDeathLeft[11] = dragonTexturesSheet.crop(x * 8, y * 9);
        dragonDeathLeft[12] = dragonTexturesSheet.crop(x * 7, y * 9);
        dragonDeathLeft[13] = dragonTexturesSheet.crop(x * 6, y * 9);
        dragonDeathLeft[14] = dragonTexturesSheet.crop(x * 5, y * 9);
        dragonDeathLeft[15] = dragonTexturesSheet.crop(x * 4, y * 9);
        dragonDeathLeft[16] = dragonTexturesSheet.crop(x * 3, y * 9);
        dragonDeathLeft[17] = dragonTexturesSheet.crop(x * 2, y * 9);
        dragonDeathLeft[18] = dragonTexturesSheet.crop(x, y * 9);
        dragonDeathLeft[19] = dragonTexturesSheet.crop(0, y * 9);
        dragonDeathLeft[20] = dragonTexturesSheet.crop(x * 9, y * 7);
        dragonDeathLeft[21] = dragonTexturesSheet.crop(x * 8, y * 7);
        dragonDeathLeft[22] = dragonTexturesSheet.crop(x * 7, y * 7);

        dragonFireballRight[0] = dragonTexturesSheet.crop(0, y * 3);
        dragonFireballRight[1] = dragonTexturesSheet.crop(x, y * 3);
        dragonFireballRight[2] = dragonTexturesSheet.crop(x * 2, y * 3);
        dragonFireballRight[3] = dragonTexturesSheet.crop(x * 3, y * 3);
        dragonFireballRight[4] = dragonTexturesSheet.crop(x * 3, y * 3);
        dragonFireballRight[5] = dragonTexturesSheet.crop(x * 2, y * 3);
        dragonFireballRight[6] = dragonTexturesSheet.crop(x, y * 3);
        dragonFireballRight[7] = dragonTexturesSheet.crop(0, y * 3);

        dragonFireballLeft[0] = dragonTexturesSheet.crop(x * 7, y * 4);
        dragonFireballLeft[1] = dragonTexturesSheet.crop(x * 6, y * 4);
        dragonFireballLeft[2] = dragonTexturesSheet.crop(x * 5, y * 4);
        dragonFireballLeft[3] = dragonTexturesSheet.crop(x * 4, y * 4);
        dragonFireballLeft[4] = dragonTexturesSheet.crop(x * 4, y * 4);
        dragonFireballLeft[5] = dragonTexturesSheet.crop(x * 5, y * 4);
        dragonFireballLeft[6] = dragonTexturesSheet.crop(x * 6, y * 4);
        dragonFireballLeft[7] = dragonTexturesSheet.crop(x * 7, y * 4);


    }

}

