package bluej;

import java.util.Scanner;
public class dmg_calc_beta
{
    public static String[] types = {"Bug","Dark","Dragon","Electric","Fairy","Fighting","Fire","Flying","Ghost","Grass","Ground","Ice","Normal","Poison","Psychic"};
    public static void main()
    {
        int i,tstr,pid,uid,aoe=1,bp=1,smd=0,smv=0;
        long dmg;
        double off,roll,psv;
        String yn="(Type yes to continue or no to decline then hit Enter to confirm your choice.)",nl="\0\0\0\n",
               ch="Give your choice in an integer number in the range of 1-",mvnam="";
        int[] uc,mset,wthr={0,0,0,0},minfo={0,0,0,0};
        double[] def=new double[3];
        int[][] field=new int[3][4];
        // 1 - Aoe, 2 - Supereffective, 3 - Crit, 4 - Passive Skills");
        Scanner in = new Scanner(System.in);
        for (i=0; i<3; i++)
            field[i]=setAllint(field[i],1);
        GameText("Welcome to Pokemon Masters Damage Calculator!"
                     +nl+"You can search only for trainers."
                     +nl+"For ex. if you search for MC or Main Character, I will show you all the sync pairs associated with that trainer."
                     +nl+"For which unit you want to calculate damage for?");
        tstr=returnID(trnrs(), in.nextLine());
        GameText(nl+"Wait... Let me check, if there is any trainer with this name.");
        if (trnrs()[tstr].equals("NA"))
        {
            GameText("I cannot find any trainer with that name. Maybe this trainer is not usable in the game yet.");
            showError(1,"Invalid Input. No such trainer found.");
            System.exit(0);
        }
        GameText("Oh, you want to calculate damage for sync pairs which include the trainer "+trnrs()[tstr]+"."
                     +nl+nl+"I will show you the sync pairs related to this trainer.");
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
        uid=tstr;
        pid=uc[tstr]%1000000;
        GameText(nl+"This means you want to calculate damage for "+returnSP(uc[tstr])
                     +nl+"Choose the move for which you want to calculate the damage:");
        mset=msets()[pid%1000-1];
        for (i=1; i<=mset.length; i++)
            GameText(i+". "+moves()[(mset[i-1]-1)%1000]);
        GameText(i+". "+smoves()[pid%1000-1]+
                      nl+ch+(mset.length+1));
        tstr=Integer.parseInt(in.next())-1;
        if (tstr<0||tstr>mset.length)
        {
            showError(3,"Invalid Input. There is no move at that place.");
            System.exit(0);
        }
        if (tstr==mset.length)
        {
            mvnam=smoves()[pid%1000-1];
            minfo[0]=sminfo()[pid%1000-1][0];
            minfo[2]=sminfo()[pid%1000-1][1];
            minfo[3]=sminfo()[pid%1000-1][2];
        } else {
            smd=mset[tstr]/1000;
            minfo=minfo()[(mset[tstr]-1)%1000];
            mvnam=moves()[(mset[tstr]-1)%1000];
        }
        GameText(nl+"This means you want to calculate damage for "+mvnam+"."
                     +nl+nl+"Move Info:-"+nl+"Base Power: "+minfo[0]
                     +nl+"Category: "+((minfo[2]==1)?"Special":"Physical")
                     +nl+"Reach: "+((minfo[1]==1)?"All opponents":"An opponent")
                     +nl+"Type: "+types[minfo[3]-1]
                     +nl+nl+"Please give the current sync move level of the sync pair."+nl+ch+"5");
        tstr=Integer.parseInt(in.next());
        if (tstr<=0||tstr>5)
        {
            showError(4,"Invalid Input. Sync Move Level "+tstr+" not possible.");
            System.exit(0);
        }
        bp=minfo[0]*((100+(5*(tstr-1)))/100);
        off=giveOffence(uid,smd,minfo[2],mvnam);
        if (minfo[1]==1)
        {
            GameText(nl+"This move can damage more than one opponents"
                        +nl+"How many opponents were on the field when the move was used?");
            tstr=Integer.parseInt(in.next());
            if (!(tstr>=1&&tstr<=3))
            {
                showError(5,"Invalid Input. "+tstr+" opponents not possible.");
                System.exit(0);
            }
            field[0][0]=tstr;
            for (i=0; i<field[0][0]&&(!(field[0][0]==1)); i++)
            {
                field[i][0]=tstr;
                GameText(nl+"Was the move super-effective on the "+((i==0)?"first":((i==1)?"second":"third"))+" opponent? "+yn);
                if (in.next().toLowerCase().charAt(0)=='y')
                    field[i][1]=2;
                GameText(nl+"Was the move a critical hit on the "+((i==0)?"first":((i==1)?"second":"third"))+" opponent? "+yn);
                if (in.next().toLowerCase().charAt(0)=='y')
                    field[i][2]=2;
                def[i]=giveDefense(i, field[i][2], minfo[2]);
            }
        }
        if (minfo[1]==0||field[0][0]==1)
        {
            GameText(nl+"Was the move super-effective on the opponent? "+yn);
            if (in.next().toLowerCase().charAt(0)=='y')
                field[0][1]=2;
            GameText(nl+"Was the move a critical hit on the opponent? "+yn);
            if (in.next().toLowerCase().charAt(0)=='y')
                field[0][2]=2;
            def[0]=giveDefense(3, field[0][2], minfo[2]);
        }
        GameText("Are there any passive skills affecting the damage of the move? "+yn);
        if (minfo[1]==1)
        {
            for (i=0; i<field[0][0]; i++)
            {
                roll=((int)(Math.random()*10)+90)/100;
                dmg=(long)Math.floor((bp*(off/def[i])+1)*roll);
                GameText("Damage on the "+((i==0)?"first":((i==1)?"second":"third"))+" opponent = "+dmg);
            }
        } else {
            roll=(Math.random()+90)/100.0;
            dmg=(long)Math.floor((bp*(off/def[0])+1)*roll);
            GameText(nl+"Damage on the opponent = "+dmg);
        }
        in.close();
    }
    public static String returnSP(int uid)
    {
        int pid=uid%1000000;
        String sp=" and "+pkmns()[pid%1000-1],trnr=trnrs()[pid/1000];
        switch (uid/10000000)
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
    public static double giveOffence(int uid, int smd, int cat, String mvnam)
    {
        Scanner in = new Scanner(System.in);
        int bof,gear,grid,buff,smb;
        String nl="\0\0\0\n",ch="Give your choice in an integer number in a range of ";
        GameText(nl+"Please give the "+((cat==1)?"Special ":"")+"Attack stat of the pair.");
        bof=Integer.parseInt(in.next());
        if (bof<1)
        {
            showError(10, "Invalid Input. "+bof+" "+((cat==1)?"Special ":"")+"Attack stat not possible.");
            System.exit(0);
        }
        GameText(nl+"Please give the additions to the "+((cat==1)?"Special ":"")+"Attack stat of the pair due to gear."
                     +nl+ch+"3-40");
        gear=Integer.parseInt(in.next());
        if ((gear<3||gear>40)&&!(gear==0))
        {
            showError(11, "Invalid Input. No Gear can give "+((gear<0)?"":"+")+gear+" "+((cat==1)?"Special ":"")+"Attack stat.");
            System.exit(0);
        }
        GameText(nl+"Please give the additions to the "+((cat==1)?"Special ":"")+"Attack stat of the pair due to the Sync Grid."
                     +nl+"Please give your choice in multiples of 5, such as 5, 10, 15, 20 etc."
                     +nl+"Note: Any value which is not a multiple of 5 will be rounded down to the nearest multiple of 5");
        grid=Integer.parseInt(in.next());
        if (grid<0)
        {
            showError(12, "Invalid Input. The Sync Grid cannot give "+gear+" "+((cat==1)?"Special ":"")+"Attack stat.");
            System.exit(0);
        }
        grid-=grid%5;
        GameText(nl+"Please give any in-battle buffs to the "+((cat==1)?"Special ":"")+"Attack stat of the user"
                     +nl+ch+"0-6");
        buff=Integer.parseInt(in.next());
        if (buff<0||buff>6)
        {
            GameText("Error e12: This calculator doesn't support "+((buff<0)?"":"+")+buff+" "+((cat==1)?"Sp.":"")+"Atk. buff yet."
                         +nl+"Sorry for any inconvinence caused.");
            buff=(buff<0)?0:6;
        }
        GameText(nl+"How many sync moves were used by the attacker before using this move?");
        smb=Integer.parseInt(in.next());
        if (smb<0)
        {
            showError(13, "Invalid Input.");
            System.exit(0);
        }
        if (smd==1&&smb==0)
        {
            showError(14,"Invalid Input. The Sync pair \""+returnSP(uid)+"\" cannot use "+mvnam+" before using atleast one sync move itself.");
            System.exit(0);
        }
        return Math.floor((bof+gear+grid)*((buff==0)?1:((buff==1)?1.25:1+(buff+2.0)/10.0)))*(1.0+smb/2.0);
    }
    public static double giveDefense(int i, int crit, int cat)
    {
        Scanner in = new Scanner(System.in);
        int def,buff=0,smb=0;
        String nl="\0\0\0\n";
        GameText(nl+"Please give the "+((cat==1)?"Special ":"")+"Defense stat of the"+
                         ((i==0)?"first":((i==1)?"second":((i==2)?"third":"")))+" opponent.");
        def=Integer.parseInt(in.next());
        if (def<1)
        {
            showError(20, "Invalid Input. "+def+" "+((cat==1)?"Special ":"")+"Defense stat not possible.");
            System.exit(0);
        }
        if (crit==1)
        {
            GameText(nl+"Please give any in-battle buffs to the "+((cat==1)?"Special ":"")+"Defense stat of the target"
                         +nl+"Give your choice in an integer number in a range of 0-6");
            buff=Integer.parseInt(in.next());
            if (buff<0||buff>6)
            {
                GameText("Error e21: This calculator doesn't support "+((buff<0)?"":"+")+buff+" "+((cat==1)?"Sp.":"")+"Def. buff yet."
                             +nl+"Sorry for any inconvinence caused.");
                buff=(buff<0)?0:6;
            }
            GameText(nl+"How many sync moves were used by the opponent side before using this move?");
            smb=Integer.parseInt(in.next());
            if (smb<0)
            {
                showError(22, "Invalid Input.");
                System.exit(0);
            }
        }
        return Math.floor(def*((buff==0)?1:((buff==1)?1.25:1+(buff+2.0)/10.0)))*(1.0+smb/2.0);
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
                         {1080,1024},{77,76},{2138,111,1050,1016,1140},{41,34},{2065,2015},{1065,1057,1015},{2110},{1111},{96,120},{127,125},
                         {102,53},{44,92,99},{19,149},{117,129,98},{2086,2060},{1027,1060},{2063,2081},{1063,1081},{112,119},{112,119},
                         {112,119},{122,22},{52,92},{52,92},{52,92},{104,151,124},{3,105},{120,101},{130,147,70}};
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
                         {81130,81131,31081132},{82133},{83134},{10084135,10084136},{85137,85138},{86139},{87140},{88141},{89142},
                         {90143},{91144},{92145,92146},{93147,93148},{94149,94150,94151},{95152},{96153,96154,96155},{97156},{98157},{99158},
                         {100159}};
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
                          "Mega Metagross","Alolan Sandslash","Mew","Sylveon","Charizard","Mega Charizard X","Alakazam","Mega Alakazam","Mismagius","Salazzle",
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
                          "Ethan","Leaf","Lance","Cynthia","Prof. Kukui","Zinnia","Steven","Prof. Oak","Valerie","Red",
                          "Sabrina","Fantina","Plumeria","Guzma","Clemont","Wallace","Silver","Wally","Jasmine","Dawn","Burgh",
                          "Serena","Lucy","Jessie","Morty","Cyrus","NA"};
        return trnrs;
    }
    public static String[] smoves()
    {
        String[] smoves = {"Thunder of Newfound Passion","Fire Sync Impact","Shining Friendship Sunraze Smash","Rock-Solid Rockslide","Sygnature Rock-Solid Stone Edge","Tomboyish Mermaid Bubble Beam","Electric Sync Beam","Surging Charge Beam","Nature-Loving Petal Dance","Fire Sync Impact",
                           "Quizmaster Flame Charge","Freezing Tower Blizzard","Trained-to-the-Max Dynamic Punch","Tried-and-True Hex","Tried-and-True Hex","Water Sync Impact","Water Sync Impact","Crystalline Aqua Tail","Bug Expert Twineedle","Bug Expert Twineedle",
                           "Supercute Rolling Tackle","Ice Sync Beam","Winter's Lesson Aurora Beam","No Mercy Dragon Pulse","Ninja Spirit Cross Poison","Mystery Masquerade Psychic","Beguiling Dark Pulse","Beguiling Dark Pulse","Grass Sync Impact","Grass Sync Impact",
                           "Leaf Blade of Sundering","Rock Sync Beam","Honor Student Power Gem","Fighting Sync Impact","Arm Thrust of Mighty Waves","Fiery Passion Overheat","Power-Chasing Giga Impact","Flyaway Air Cutter","Zen Headbutt of Duality","Psychic of Duality",
                           "Ghost Sync Impact","Lonely Flower Shadow Punch","Righteous Heart Dragon Claw","Factory Head X-Scissor","Factory Head X-Scissor","Water Sync Beam","Water Sync Beam","Late Fee Bubble Beam","Rock Sync Impact","Rock Head Smash",
                           "Vivid Leaf Storm","Fighting Sync Impact","Barefoot High Jump Kick","Crashdown Aqua Jet","All-about-Focus Avalanche","Burn-It-All Overheat","Post-analysis Flash Cannon","Blissful Echo Hyper Voice","Grateful Friend Flare Blitz","Grass Sync Beam",
                           "Grass Sync Beam","Shoot for the Stars Leaf Storm","Special Delivery Drill Peck","Ground Sync Impact","Rock-Bottom Bulldoze","High-Flying Sky Attack","Lights, Camera, Ice Shard","Dragon Sage Outrage","Poison Sync Impact","Radical Poison Jab",
                           "Oversplash Aqua Tail","Dark Tales of the Shadow Ball","Way-of-the-Warrior Focus Punch","Rock Sync Impact","Crazy Cool Rock Tomb","Give-It-All-Ya-Got Power-Up Punch","Give-It-All-Ya-Got Power-Up Punch","Grass Sync Impact","Old Growth Razor Leaf","Unstoppable Avalanche",
                           "Water Pulse Du Jour","Gourmet Octazooka","Shining Knight Iron Head","Shining Knight Iron Head","Endless Summer Gigavolt Havoc","Whiz Kid Gigavolt Havoc","Wandering Artist Twinkle Tackle","Shining Gem Continental Crush","Ultimately Worthy Tectonic Rage","Supersonic Skystrike Drive",
                           "Bug Sync Beam","Silver Wind Victory Shot","Dark Authority Black Hole Eclipse","Modern Ninja Sludge Bomb","Never-Ending Royal Nightmare","Fundamental Takedown","World-Swallowing Hurricane","World-Swallowing Hurricane","Grass Sync Beam","Grass Sync Beam",
                           "Heart of Gold Leaf Storm","Cute Factor Disarming Voice","Water Sync Impact","Water Sync Impact","The True Razor Shell","Fire Sync Impact","Fire Sync Impact","Battle Fanatic Heat Crash","Shining Spotlight Wild Bolt","High Fashion Thunderbolt",
                           "Broken Sleep Psychic","Psychic Sync Beam","Mind-Bending Psychic","Glacial Freeze-Dry","Glacial Freeze-Dry","Card Shark Night Slash","High-Stakes Liquidation","High-Stakes Liquidation","World Domination Psystrike","Fire Sync Beam",
                           "Fire Sync Beam","Fierce Flames Eruption","Endless Possibilities Extreme Evoboost","Unrivaled Outrage","Earthquake of Ancient Lore","Earthquake of Ancient Lore","Historymaker Clanging Scales","Island Splintered Stormshards","Dimensional Defender Draco Meteor","Solid Steel Meteor Mash",
                           "Solid Steel Meteor Mash","Too Cool Icicle Crash","Pokémon Professor Psychic","Fashionista Dazzling Gleam","Living Legend Blast Burn","Living Legend Blast Burn","ESP Prodigy Psywave","ESP Prodigy Psywave","Soulful Dancer Shadow Ball","Wicked Enforcer Acid Downpour",
                           "Your Boy's X-Scissor","Light-Up-the-World Thunderbolt","Aquatic Prince Hydro Pump","Power-Hungry Sacred Fire","Rallying Close Combat","Rallying Close Combat","Steel-Clad Iron Tail","Steel-Clad Iron Tail","Grass Sync Impact","Grass Sync Impact",
                           "New Dawn Wood Hammer","Insectible Struggle Bug","Fire Sync Beam","Fire Sync Beam","Mystical Fire of Positivity","Pike Queen Poison Fang","Prepare for Trouble Acid","Mystic Seer Phantom Force","Emotional Void Spacial Rend",};
        return smoves;
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
    public static int[][] sminfo()
    {
        int[][] sminfo = {{250,1,4},{200,0,7},{250,0,17},{200,0,16},{250,0,16},{200,1,18},{200,1,4},{200,1,4},{200,1,10},{200,0,7},
                          {200,0,7},{200,1,12},{250,0,6},{160,1,9},{160,1,9},{200,0,18},{200,0,18},{250,0,18},{160,0,1},{160,0,1},
                          {250,0,13},{200,1,12},{250,1,12},{250,1,3},{200,0,14},{200,1,15},{160,1,2},{160,1,2},{200,0,10},{200,0,10},
                          {250,0,10},{200,1,16},{250,1,16},{200,0,6},{200,0,6},{200,1,7},{300,0,13},{200,1,8},{250,0,15},{250,1,15},
                          {200,0,9},{250,0,9},{250,0,3},{160,0,1},{160,0,1},{200,1,18},{200,1,18},{250,1,18},{200,0,16},{250,0,16},
                          {250,1,10},{200,0,6},{250,0,6},{200,0,18},{200,0,12},{250,1,7},{200,1,17},{300,1,13},{200,0,7},{200,1,10},
                          {200,1,10},{250,1,10},{250,0,8},{200,0,11},{200,0,11},{200,0,8},{200,0,12},{250,0,3},{200,0,14},{250,0,14},
                          {200,0,18},{250,1,9},{250,0,6},{200,0,16},{200,0,16},{160,0,6},{160,0,6},{200,0,10},{200,0,10},{250,0,12},
                          {250,1,18},{200,1,18},{200,0,17},{200,0,17},{250,1,4},{200,0,4},{200,0,5},{250,0,16},{250,0,11},{250,0,8},
                          {200,1,1},{200,1,1},{200,0,2},{200,1,14},{200,0,9},{250,0,13},{160,1,8},{160,1,8},{200,1,10},{200,1,10},
                          {200,1,10},{250,1,5},{200,0,18},{200,0,18},{250,0,18},{200,0,7},{200,0,7},{250,0,7},{250,0,4},{250,1,4},
                          {250,1,15},{200,1,15},{200,1,15},{160,1,12},{160,1,12},{200,0,2},{160,0,18},{160,0,18},{250,1,15},{200,1,7},
                          {200,1,7},{250,1,7},{0,1,13},{250,0,3},{160,0,11},{160,0,11},{0,2,3},{250,0,16},{250,1,3},{160,0,17},
                          {160,0,17},{250,0,12},{250,1,15},{250,1,5},{160,1,7},{160,1,7},{160,1,15},{160,1,15},{250,1,9},{250,1,14},
                          {250,0,1},{200,1,4},{200,1,18},{250,0,7},{160,0,6},{160,0,6},{160,0,17},{160,0,17},{200,0,10},{200,0,10},
                          {200,0,10},{250,1,1},{200,1,7},{200,1,7},{250,0,9},{200,0,14},{250,1,14},{200,1,7},{0,1,3},{0,0,0}};
        return sminfo;
    }
    public static int[][] minfo()
    {
        int[][] minfo = {{19,0,1,10},{75,0,0,16},{19,0,1,14},{45,0,1,8},{43,1,1,8},{45,0,1,8},{47,0,1,16},{75,0,0,18},{56,0,0,18},{12,0,0,9},
                         {45,0,1,6},{75,0,0,12},{75,0,0,8},{42,0,0,2},{178,0,1,7},{126,1,1,12},{53,0,0,13},{35,0,0,6},{38,0,1,18},{19,1,1,18},
                         {49,0,1,18},{99,0,1,1},{80,1,0,11},{75,0,0,17},{15,0,0,10},{176,1,1,3},{124,0,0,6},{18,0,1,15},{100,0,0,6},{38,0,0,14},
                         {99,0,0,2},{95,0,1,2},{50,1,1,5},{45,1,1,5},{92,1,1,4},{167,0,0,13},{136,0,1,3},{42,0,1,3},{50,0,0,3},{91,0,0,6},
                         {46,0,1,5},{100,0,0,8},{100,1,0,11},{39,1,1,4},{17,0,1,7},{49,0,1,10},{135,1,1,7},{47,0,0,13},{44,0,0,1},{116,0,1,7},
                         {47,0,0,7},{38,0,1,7},{200,0,0,1},{30,0,0,7},{42,0,0,7},{47,0,1,7},{123,0,0,7},{38,1,1,12},{8,0,0,1},{140,0,1,6},
                         {91,0,1,10},{20,0,1,8},{44,0,0,17},{188,0,0,16},{109,1,1,7},{38,0,1,9},{50,0,1,15},{106,0,0,11},{126,0,1,8},{125,0,1,18},
                         {223,0,1,13},{63,1,1,13},{46,0,1,12},{46,0,0,12},{46,0,0,12},{105,0,0,12},{15,0,0,12},{92,1,1,12},{20,0,1,1},{92,0,0,17},
                         {132,0,0,17},{16,0,0,6},{136,0,1,10},{12,0,0,9},{99,0,0,18},{30,0,0,6},{167,0,0,13},{21,0,0,17},{55,0,0,17},{100,0,1,15},
                         {39,0,1,11},{13,0,1,11},{40,0,0,2},{20,0,0,4},{56,0,1,18},{47,0,1,9},{115,0,0,3},{136,0,1,7},{46,1,1,4},{20,0,0,8},
                         {160,0,0,9},{16,0,0,1},{111,0,0,5},{34,0,0,14},{92,0,0,14},{12,0,0,14},{38,0,0,14},{16,1,1,12},{30,0,0,6},{48,0,1,15},
                         {99,0,1,15},{43,1,0,10},{105,1,0,16},{46,0,0,6},{23,0,0,16},{39,0,0,16},{87,0,0,7},{87,0,1,18},{100,0,0,10},{99,0,1,9},
                         {45,0,0,9},{47,0,1,1},{50,0,0,13},{92,0,1,14},{97,0,1,14},{20,0,0,16},{21,0,1,14},{39,1,1,2},{125,0,1,10},{85,0,1,3},
                         {42,0,0,4},{53,0,0,13},{44,0,0,11},{100,0,0,16},{13,0,0,15},{30,1,1,1},{100,0,0,17},{48,1,1,13},{25,0,0,13},{124,0,1,4},
                         {17,0,1,4},{47,0,1,4},{20,0,0,1},{20,0,1,6},{42,0,1,14},{20,0,0,10},{47,0,1,18},{45,0,0,18},{38,0,1,18},{125,0,0,4},
                         {39,0,0,13},{50,0,0,1},{51,0,0,15},{42,0,0,4}};
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