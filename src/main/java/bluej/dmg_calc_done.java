package bluej;

import java.util.*;
public class dmg_calc_done
{
    public static void main()
    {
        Scanner in = new Scanner(System.in);
        int i,tstr,pid,uid,mid,aoe=1,off,bp=0;
        long dmg,min;
        double mod,roll;
        String ch = "Give your choice in an integer number between 1-";
        int[] uc,mset,def=new int[3];
        int[][] mods=new int[3][10];
        for (i=0; i<3; i++)
            mods[i]=setAllint(mods[i],1);
        String yn = "(Type yes to continue or no to decline then hit Enter to confirm your choice.)";
        String n3="\0\0\0",nl=n3+"\n";
        GameText("Welcome to Pokemon Masters Damage Calculator!");
        GameText("You can search only for trainers.");
        GameText("For ex. if you search for MC or Main Character, I will show you all the pokemon associated with that trainer.");
        GameText("For which unit you want to calculate damage for?");
        tstr=returnID(trnrs(), in.nextLine());
        GameText(nl+"Wait... Let me check, if there is any trainer with this name.");
        if (trnrs()[tstr].equals("NA"))
        {
            GameText("I cannot find any trainer with that name. Maybe this trainer is not usable in the game yet.");
            showError(1,"Invalid Input. No such trainer found.");
            System.exit(0);
        }
        GameText("Oh, you want to calculate damage for sync pairs which include the trainer "+trnrs()[tstr]+"."+nl);
        GameText("I will show you the sync pairs related to this trainer.");
        tstr=(tstr==0)?1:tstr;
        uc=tdabs()[tstr-1];
        for (i=0; i<uc.length; i++)
            GameText((i+1)+". "+returnSP(uc[i]));
        if (uc.length==1)
            tstr=0;
        else
        {
            GameText(ch+uc.length);
            tstr=Integer.parseInt(in.next())-1;
            if (tstr<0||tstr>=uc.length)
            {
                showError(2,"Invalid Input. There is no trainer at that place.");
                System.exit(0);
            }
        }
        GameText(nl+"This means you want to calculate damage for "+returnSP(uc[tstr]));
        uid=tstr;
        pid=uc[tstr]%1000000;
        GameText("Choose the move for which you want to calculate the damage:");
        mset=msets()[pid%1000-1];
        for (i=0; i<mset.length; i++)
            GameText((i+1)+". "+moves()[(mset[i]-1)%1000]);
        if (mset.length==1)
            mid=0;
        else
        {
            GameText(ch+mset.length);
            mid=Integer.parseInt(in.next())-1;
            if (mid<0||mid>=mset.length)
            {
                showError(3,"Invalid Input. There is no move at that place.");
                System.exit(0);
            }
        }
        min=mset[mid]/1000;
        mid=(mset[mid]-1)%1000;
        GameText(nl+"This means you want to calculate damage for "+moves()[mid]+"."+nl);
        GameText("Move Info:-"+nl+"Base Power: "+minfo()[mid][0]+nl+"Category: "+((minfo()[mid][2]==1)?"Special":"Physical"));
        GameText("Reach: "+((minfo()[mid][1]==1)?"All opponents":"An opponent")+nl);
        GameText("Please give the current sync move level of the sync pair."+nl+ch+"5");
        tstr=Integer.parseInt(in.next());
        if (tstr<=0||tstr>5)
        {
            showError(4,"Invalid Input. Sync Move Level "+tstr+" not possible.");
            System.exit(0);
        }
        bp=minfo()[mid][0]*((100+(5*(tstr-1)))/100);
        GameText(nl+"Please give the "+((minfo()[mid][2]==1)?"Special ":"")+"Attack stat of the pair.");
        off=Integer.parseInt(in.next());
        if (off<1)
        {
            showError(5, "Invalid Input. "+off+" "+((minfo()[mid][2]==1)?"Special ":"")+"Attack stat not possible.");
            System.exit(0);
        }
        if (minfo()[mid][1]==1)
        {
            GameText(nl+"This move can damage more than one opponents");
            GameText("How many opponents were on the field when the move was used?");
            tstr=Integer.parseInt(in.next());
            if (!(tstr>=1&&tstr<=3))
            {
                showError(7,"Invalid Input. "+tstr+" opponents not possible.");
                System.exit(0);
            }
            mods[0][0]=tstr;
            for (i=0; i<mods[0][0]&&(!(mods[0][0]==1)); i++)
            {
                mods[i][0]=tstr;
                GameText(nl+"Was the move super-effective on the "+((i==0)?"first":((i==1)?"second":"third"))+" opponent? "+yn);
                if (in.next().toLowerCase().charAt(0)=='y')
                    mods[i][1]=2;
                GameText(nl+"Was the move a critical hit on the "+((i==0)?"first":((i==1)?"second":"third"))+" opponent? "+yn);
                if (in.next().toLowerCase().charAt(0)=='y')
                    mods[i][2]=2;
                GameText(nl+"Please give the "+((minfo()[mid][2]==1)?"Special ":"")+"Defense stat of the "+((i==0)?"first":((i==1)?"second":"third"))+" opponent.");
                def[i]=Integer.parseInt(in.next());
                if (def[i]<=0)
                {
                    showError(6, "Invalid Input. "+def[i]+" "+((minfo()[mid][2]==1)?"Special ":"")+"Defense stat not possible.");
                    System.exit(0);
                }
            }
        }
        if (minfo()[mid][1]==0||mods[0][0]==1)
        {
            GameText(nl+"Was the move super-effective on the opponent? "+yn);
            if (in.next().toLowerCase().charAt(0)=='y')
                mods[0][1]=2;
            GameText(nl+"Was the move a critical hit on the opponent? "+yn);
            if (in.next().toLowerCase().charAt(0)=='y')
                mods[0][2]=2;
            GameText(nl+"Please give the "+((minfo()[mid][2]==1)?"Special ":"")+"Defense stat of the opponent.");
            def[0]=Integer.parseInt(in.next());
            if (def[0]<1)
            {
                showError(6, "Invalid Input. "+def[0]+" "+((minfo()[mid][2]==1)?"Special ":"")+"Defense stat not possible.");
                System.exit(0);
            }
        }
        mods[0][3]=0;
        if (!(min==2))
        {
            GameText(nl+"How many sync moves were used by the attacker before using this move?");
            tstr=Integer.parseInt(in.next());
            for (i=0; i<mods[0][0]; i++)
                mods[i][3]=tstr;
            if (mods[0][3]<0)
            {
                showError(8, "Invalid Input.");
                System.exit(0);
            }
            if (min==1&&mods[0][3]==0)
            {
                showError(9, "Invalid Input. The Sync pair \""+returnSP(uc[uid])+"\" cannot use \""+moves()[mid]+"\" before using a sync move.");
                System.exit(0);
            }
        }
        if (minfo()[mid][1]==1)
        {
            for (i=0; i<mods[0][0]; i++)
            {
                mod=((mods[i][0]==2)?2/3.0:((mods[i][0]==3)?0.5:1))*mods[i][1]*((mods[i][2]==2)?1.5:1)*Math.pow(1.5,((mods[i][3]>8)?9:mods[i][3]));
                roll=(Math.random()+9)/10;
                dmg=(long)Math.floor((bp*(off/def[i])+1)*mod*roll);
                GameText("Damage on the "+((i==0)?"first":((i==1)?"second":"third"))+" opponent = "+dmg);
            }
        } else {
            mod=mods[0][1]*((mods[0][2]==2)?1.5:1)*Math.pow(1.5,((mods[0][3]>8)?9:mods[0][3]));
            roll=(Math.random()+9)/10;
            dmg=(long)Math.floor((bp*(off/def[0])+1)*mod*roll);
            GameText(nl+"Damage on the opponent = "+dmg);
        }
    }
    public static String returnSP(int uid)
    {
        int pid=uid%1000000;
        String sp=" and "+pkmns()[pid%1000-1],trnr=trnrs()[pid/1000];
        switch (pid/10000000)
        {
            case 1:
            trnr="Sygna Suit "+trnr;
            break;
            case 2:
            trnr+="(Holiday 20"+(19+(uid/1000000)%10)+")";
            break;
            case 3:
            trnr+="(Summer 20"+(19+(uid/1000000)%10)+")";
        }
        return trnr+sp;
    }
    public static int[][] msets()
    {
        int[][] msets = {{141,142},{54,56},{88,137,60},{115,116},{116,113},{21,70},{142},{142},{46},{55,52},
                         {55,52},{73,16},{82,29},{2084,2120},{1066,1120},{148,87},{148,87},{148,87},{143,49},{143,49},
                         {70,132},{73,78},{73,78},{38,37},{30},{135,6},{2014,2128},{1128,1032},{25,83},{25,83},
                         {25,83},{7},{7},{18,114},{18,114},{17},{17,36},{6,69},{116,153},{28,111},
                         {121,36},{121,36},{38,39},{2059,2152},{1059,1152},{20,21},{20,21},{20,21},{126,64},{126,64},
                         {1,83},{114,40},{114,40},{148,147},{75},{51,50},{63,153},{56,72},{56,55},{46,61},
                         {46,61},{46,61},{4,42},{91,23},{91,23},{62,4},{7,73},{39,97},{107,105},{107,105},
                         {9,116},{66,120},{114,40},{116,113},{116,113},{2144,2027},{1109,1027},{25},{25},{74,12},
                         {11,147},{118,95},{63,80},{63,80},{141,35},{94,154},{33,103},{115,134},{92,68},{100,13},
                         {79,136},{79,136},{14},{104,145},{10},{48,31},{2006,2005},{1005,1069},{46},{46},
                         {46},{33,103},{9,148},{9,148},{9,148},{54,57},{54,57},{54,57},{131,150},{141,140},
                         {67,111},{110},{110},{2108,2073},{1058,1073},{93,32},{2148,2085},{1008,1085},{28,111,120},{56,47},
                         {56,47},{56,47},{139,81},{39,71},{2133,2043},{43,123},{38,26},{116,2},{97,4,37},{2089,2080},
                         {1080,1024},{77,76},{2138,111,1050,1016,1140},{2065,2015},{1065,1057,1015},{2110},{1111},{96,120},{127,125},{102,53},
                         {44,92,99},{19,149},{117,129,98},{2086,2060},{1027,1060},{2063,2081},{1063,1081},{112,119},{112,119},{112,119},
                         {122,22},{52,92},{52,92},{52,92},{104,151,124},{3,105},{120,101},{130,147,70}};
        return msets;
    }
    public static int[][] tdabs()
    {
        int[][] tdabs = {{1001,1002,1003},{2004,10002005},{3006},{4007,4008},{5009},{6010,6011},{7012},{8013},{9014,9015},{10016,10017,10018},
                         {11019,11020},{12021},{13022,13023},{14024},{15025},{16026},{17027,17028},{18029,18030,18031},{19033},{20034,20035},
                         {21036},{22037},{23038},{24039},{25040},{26041,26042},{27043},{28044,28045},{29046,29047,29048},{30049,305050},
                         {31051},{32052,3253},{33054},{34055},{35056},{36057},{37058},{38059},{39060,39061,39062,20039063},{40064,40065},
                         {41066},{42067},{43068},{44069,44070},{45071},{46072},{47073},{48074,48075},{49076,49077},{50078,50079},
                         {51080},{52081,20052081},{53083,53084},{54085},{55086},{56087},{57088},{58089},{59090},{60091,60092},
                         {61093},{62094},{63095},{64096},{65097,65098},{66099,66100,66101,31066102},{67103,67104,67105},{68106,68107,68018},{69109,10069110},{70111},
                         {71112,71113},{72114,72115},{73116,10073117,10073118},{74119},{75120,75121,75122},{76123},{77124},{78125,78126,10078127},{79128},{80129},
                         {81130,81131,31081132},{82133},{10083134,10083135},{85136,85137},{86138},{87139},{88140},{89141},{90142},
                         {91143},{92144,92145},{93146,93147},{94148,94149,94150},{95151},{96152,96153,96154},{97155},{98156},{99157},{100158}};
        return tdabs;
    }
    public static String[] pkmns()
    {
        String[] pkmns = {"Pikachu","Torchic","Solgaleo","Onix","Tyranitar","Starmie","Voltorb","Electrode","Vileplume","Ponyta",
                          "Rapidash","Lapras","Machamp","Gengar","Mega Gengar","Totodile","Croconaw","Feraligatr","Beedrill","Mega Beedrill",
                          "Miltank","Seel","Dewgong","Kingdra","Ariados","Xatu","Houndoom","Mega Houndoom","Treecko","Grovyle",
                          "Sceptile","Nosepass","Probopass","Makuhita","Hariyama","Torkoal","Slaking","Pelipper","Solrock","Lunatone",
                          "Dusclops","Dusknoir","Salamence","Pinsir","Mega Pinsir","Piplup","Prinplup","Empoleon","Cranidos","Rampardos",
                          "Roserade","Meditite","Medichamp","Floatzel","Abamasnow","Infernape","Bronzong","Blissey","Arcanine","Snivy",
                          "Servine","Serperior","Delibird","Palpitoad","Seismitoad","Swanna","Cryogonal","Haxorus","Whirliepede","Scolipede",
                          "Carracosta","Chendulure","Conkeldurr","Amaura","Aurorus","Lucario","Mega Lucario","Weepinbell","Victreebell","Avalugg",
                          "Clawitzer","Octillery","Aegislash (Sword Form)","Aegislash (Shield Form)","Alolan Raichu","Togedemaru","Granbul","Lycanroc (Midnight Form)","Mudsdale","Toucannon",
                          "Surskit","Masquerain","Persian","Crobat","Palossand","Stoutland","Pidgeot","Mega Pidgeot","Chicorita","Bayleef",
                          "Meganium","Jigglypuff","Oshawott","Dewott","Samurott","Tepig","Pignite","Emboar","Zebstrika","Rotom",
                          "Reuniclus","Espurr","Meowstic","Glalie","Mega Glalie","Liepard","Sharpedo","Mega Sharpedo","Mewtwo","Cyndaquill",
                          "Quillava","Typhlosion","Eevee","Dragonite","Garchomp","Mega Garchomp","Kommo-o","Lycanroc (Midday Form)","Rayquaza","Metagross",
                          "Mega Metagross","Alolan Sandslash","Mew","Charizard","Mega Charizard X","Alakazam","Mega Alakazam","Mismagius","Salazzle",
                          "Golisopod","Heliolisk","Milotic","Ho-oh","Gallade","Mega Gallade","Steelix","Mega Steelix","Turtwig","Grotle","Torterra",
                          "Leavanny","Fennekin","Braixen","Delphox","Seviper","Arbok","Drifblim","Palkia","NA"};
        return pkmns;
    }
    public static String[] trnrs()
    {
        String[] trnrs = {"MC","Main Character","Brock","Misty","Lt. Surge","Erika","Blaine","Lorelei","Bruno","Agatha",
                          "Kris","Bugsy","Whitney","Pryce","Clair","Janine","Will","Karen",
                          "Brendan","Roxanne","Brawly","Flannery","Norman","Winona","Tate","Liza","Phoebe","Drake","Noland",
                          "Barry","Roark","Gardenia","Maylene","Crasher Wake","Candice","Flint","Thorton","Cheryl","Marley",
                          "Rosa","Clay","Skyla","Brycen","Iris","Roxie","Marlon","Shauntal","Marshal",
                          "Grant","Korrina","Ramos","Wulfric","Siebold","Wilkstrom",
                          "Hau","Sophocles","Mina","Olivia","Hapu","Kahili",
                          "Viola","Nanu","Koga","Acerola","Cheren",
                          "Blue","Lyra","Hilbert","Hilda","Elesa","Caitlin","Calem","Glacia","Grimsley","Giovanni",
                          "Ethan","Leaf","Lance","Cynthia","Prof. Kukui","Zinnia","Steven","Prof. Oak","Red","Sabrina",
                          "Fantina","Plumeria","Guzma","Clemont","Silver","Wally","Jasmine","Dawn","Burgh","Serena",
                          "Lucy","Jessie","Morty","Cyrus","NA"};
        return trnrs;
    }
    public static String[] moves()
    {
        String[] moves = {"Absorb","Accelerock","Acid","Aerial Ace","Air Cutter","Air Slash","Ancient Power","Aqua Jet","Aqua Tail","Astonish",
                          "Aura Sphere","Avalanche","Beak Blast","Bite","Blast Burn","Blizzard","Body Slam","Brick Break","Brine","Bubble",
                          "Bubble Beam","Bug Buzz","Bulldoze","Bullet Punch","Bullet Seed","Clanging Scales","Close Combat","Confusion","Cross Chop","Cross Poison",
                          "Crunch","Dark Pulse","Dazzling Gleam","Disarming Voice","Discharge","Double-Edge","Draco Meteor","Dragon Breath","Dragon Claw","Drain Punch",
                          "Draining Kiss","Drill Peck","Electroweb","Earthquake","Ember","Energy Ball","Eruption","Facade","Fell Stinger","Fire Blast",
                          "Fire Punch","Fire Spin","First Impression","Flame Charge","Flame Wheel","Flamethrower","Flare Blitz","Frost Breath","Fury Cutter","Focus Blast",
                          "Giga Drain","Gust","Gyro Ball","Head Smash","Heat Wave","Hex","Hidden Power","High Horsepower","Hurricane","Hydro Pump",
                          "Hyper Beam","Hyper Voice","Ice Beam","Ice Fang","Ice Punch","Icicle Crash","Icicle Spear","Icy Wind","Infestation","Iron Head",
                          "Iron Tail","Karate Chop","Leaf Storm","Lick","Liquidation","Low Sweep","Mega Kick","Metal Claw","Meteor Mash","Mirror Coat",
                          "Mud Shot","Mud-Slap","Night Slash","Nuzzle","Octazooka","Ominous Wind","Outrage","Overheat","Parabolic Charge","Peck",
                          "Phantom Force","Pin Missile","Play Rough","Poison Fang","Poison Jab","Poison Sting","Poison Tail","Powder Snow","Power-Up Punch","Psybeam",
                          "Psychic","Razor Leaf","Rock Slide","Rock Smash","Rock Throw","Rock Tomb","Sacred Fire","Scald","Seed Bomb","Shadow Ball",
                          "Shadow Punch","Silver Wind","Slash","Sludge Bomb","Sludge Wave","Smack Down","Smog","Snarl","Solar Beam","Spacial Rend",
                          "Spark","Stomp","Stomping Tantrum","Stone Edge","Stored Power","Struggle Bug","Sunsteel Strike","Swift","Tackle","Thunder",
                          "Thunder Shock","Thunderbolt","Twineedle","Vacuum Wave","Venoshock","Vine Whip","Water Pulse","Waterfall","Whirlpool","Wild Charge",
                          "Wrap","X-Scissor","Zen Headbutt","Zing Zap","NA"};
        return moves;
    }
    public static int[][] minfo()
    {
        int[][] minfo = {{19,0,1},{75,0,0},{19,0,1},{45,0,1},{43,1,1},{45,0,1},{47,0,1},{75,0,0},{56,0,0},{12,0,0},
                         {45,0,1},{75,0,0},{75,0,0},{42,0,0},{178,0,1},{126,1,1},{53,0,0},{35,0,0},{38,0,1},{19,1,1},
                         {49,0,1},{99,0,1},{80,1,0},{75,0,0},{15,0,0},{176,1,1},{124,0,0},{18,0,1},{100,0,0},{38,0,0},
                         {99,0,0},{95,0,1},{50,1,1},{45,1,1},{92,1,1},{167,0,0},{136,0,1},{42,0,1},{50,0,0},{91,0,0},
                         {46,0,1},{100,0,0},{100,1,0},{39,1,1},{17,0,1},{49,0,1},{135,1,1},{47,0,0},{44,0,0},{116,0,1},
                         {47,0,0},{38,0,1},{200,0,0},{30,0,0},{42,0,0},{47,0,1},{123,0,0},{38,1,1},{8,0,0},{140,0,1},
                         {91,0,1},{20,0,1},{44,0,0},{188,0,0},{109,1,1},{38,0,1},{50,0,1},{106,0,0},{126,0,1},{125,0,1},
                         {223,0,1},{63,1,1},{46,0,1},{46,0,0},{46,0,0},{105,0,0},{15,0,0},{92,1,1},{20,0,1},{92,0,0},
                         {132,0,0},{16,0,0},{136,0,1},{12,0,0},{99,0,0},{30,0,0},{167,0,0},{21,0,0},{55,0,0},{100,0,1},
                         {39,0,1},{13,0,1},{40,0,0},{20,0,0},{56,0,1},{47,0,1},{115,0,0},{136,0,1},{46,1,1},{20,0,0},
                         {160,0,0},{16,0,0},{111,0,0},{34,0,0},{92,0,0},{12,0,0},{38,0,0},{16,1,1},{30,0,0},{48,0,1},
                         {99,0,1},{43,1,0},{105,1,0},{46,0,0},{23,0,0},{39,0,0},{87,0,0},{87,0,1},{100,0,0},{99,0,1},
                         {45,0,0},{47,0,1},{50,0,0},{92,0,1},{97,0,1},{20,0,0},{21,0,1},{39,1,1},{125,0,1},{85,0,1},
                         {42,0,0},{53,0,0},{44,0,0},{100,0,0},{13,0,0},{30,1,1},{100,0,0},{48,1,1},{25,0,0},{124,0,1},
                         {17,0,1},{47,0,1},{20,0,0},{20,0,1},{42,0,1},{20,0,0},{47,0,1},{45,0,0},{38,0,1},{125,0,0},
                         {39,0,0},{50,0,0},{51,0,0},{42,0,0}};
        return minfo;
    }
    public static int returnID(String[] a, String b)
    {
        for (int i = 0; i<a.length; i++)
        {
            if (b.equalsIgnoreCase(a[i]))
                return i;
        }
        return a.length-1;
    }
    public static int[] setAllint(int[] a, int b)
    {
        for (int i = 0; i<a.length; i++)
            a[i]=b;
        return a;
    }
    public static void showError(int en, String e)
    {
        GameText("Error e"+((en<10)?"0"+en:en)+": "+e+"\n\0\0\0Terminating\0\0\0.\0\0\0.\0\0\0.");
    }
    public static void GameText(String s)
    {
        s+="\n\0\0\0";
        long time;
        for (int i = 0; i<s.length(); i++)
        {
            System.out.print(s.charAt(i));
            time=System.currentTimeMillis();
            while (25>=System.currentTimeMillis()-time){}
        }
    }
}
