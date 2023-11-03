package bluej;

import java.util.Scanner;
import vpg.util.*;
public class dmg_calc_alpha
{
    public static double psv = 0;
    public static String yn="(Type yes to continue or no to decline then hit Enter to confirm your choice.)",nl="\0\0\0\n";
    public static String[] types = {"Bug","Dark","Dragon","Electric","Fairy","Fighting","Fire","Flying","Ghost","Grass","Ground","Ice","Normal","Poison","Psychic","Rock","Steel","Water"},
                           passives = {"Power Reserves",
                                       "Weather Surge","Charging Sun","Raging Rain","Surging Sand","Invigorating Hail",
                                       "Solar Flare","Shower Power","Sand Blaster",
                                       "Gritty","Confusion Boon",
                                       "Dirty Fighting","Burn Synergy","Freeze Synergy","Paralysis Synergy","Toxic Power","Good Night-mare",
                                       "Foul Fighting","Harry","Confusion Synergy","Stationary Target",
                                       "Freezer Burn","Static Shock","Rude Awakening","Flinch Hitter","Fuzzy Strike",
                                       "Superduper Effective","Double Down",
                                       "Critical Strike","Critastrophe",
                                       "Power Flux","Power Chain",
                                       "Furious Brawn","Tough Cookie","Smart Cookie","Ramming Speed","Bob and Weave",
                                       "Haymaker","Inertia","Blind Spot",
                                       "Overpower","Hunter’s Instinct","Dizzying Power","Pecking Order","Smarty-Pants","Cakewalk","NA"};
    public static int[][] field={{1,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0}},
                          stts={{2,2,2,2,2},{2,2,2,2,2},{2,2,2,2,2},{2,2,2,2,2}},
                          cond={{2,2,2},{2,2,2},{2,2,2},{2,2,2}},
                          buffs={{7,7,7,7,7,7,7},{7,7,7,7,7,7,7},{7,7,7,7,7,7,7},{7,7,7,7,7,7,7}};
    public static void main()
    {
        Scanner in = new Scanner(System.in);
        int i,tstr,pid,uid,aoe=1,bp=1,smd=0,smv=0;
        long dmg;
        double off,mod,roll;
        String mvnam="",ch="Give your choice in an integer number in the range of 1-";
        int[] uc,mset,wthr={0,0,0,0},minfo={0,0,0,0};
        double[] def=new double[3];
        // 1 - Aoe, 2 - Supereffective, 3 - Crit, 4 - Passive Skills");
        GameText.println("Welcome to Pokemon Masters Damage Calculator!"
                     +nl+"You can search only for trainers."
                     +nl+"For ex. if you search for MC or Main Character, I will show you all the sync pairs associated with that trainer."
                     +nl+"For which unit you want to calculate damage for?");
        tstr=Array.returnID(trnrs, in.nextLine());
        GameText.println(nl+"Wait... Let me check, if there is any trainer with this name.");
        exit(trnrs[tstr].equals("NA"),1,"I cannot find any trainer with that name. Maybe this trainer is not usable in the game yet.");
        GameText.println("Oh, you want to calculate damage for sync pairs which include the trainer "+trnrs[tstr]+"."
                     +nl+nl+"I will show you the sync pairs related to this trainer.");
        tstr=(tstr==0)?1:tstr;
        uc=tdabs[tstr-1];
        for (i=0; i<uc.length; i++)
            GameText.println((i+1)+". "+returnSP(uc[i]));
        if (uc.length==1)
            tstr=0;
        else
        {
            GameText.println(ch+uc.length);
            tstr=Integer.parseInt(in.next())-1;
            exit(tstr<0||tstr>=uc.length,2,"Invalid Input. There is no trainer at that place.");
        }
        uid=uc[tstr];
        pid=uc[tstr]%1000000;
        GameText.println(nl+"This means you want to calculate damage for "+returnSP(uc[tstr])
                     +nl+"Choose the move for which you want to calculate the damage:");
        mset=msets[pid%1000-1];
        for (i=1; i<=mset.length; i++)
            GameText.println(i+". "+moves[(mset[i-1]-1)%1000]);
        GameText.println(i+". "+smoves[pid%1000-1]+
                      nl+ch+(mset.length+1));
        tstr=Integer.parseInt(in.next())-1;
        exit(tstr<0||tstr>mset.length,3,"Invalid Input. There is no move at that place.");
        if (tstr==mset.length)
        {
            mvnam=smoves[pid%1000-1];
            for (i=0; i<3; i++)
                minfo[(i==0)?i:i+1]=sminfos[pid%1000-1][i];
        } else {
            smd=mset[tstr]/1000;
            minfo=minfos[(mset[tstr]-1)%1000];
            mvnam=moves[(mset[tstr]-1)%1000];
        }
        GameText.println(nl+"This means you want to calculate damage for "+mvnam+"."
                     +nl+nl+"Move Info:-"+nl+"Base Power: "+minfo[0]
                     +nl+"Category: "+((minfo[2]==1)?"Special":"Physical")
                     +nl+"Reach: "+((minfo[1]==1)?"All opponents":"An opponent")
                     +nl+"Type: "+types[minfo[3]-1]
                     +nl+nl+"Please give the current sync move level of the sync pair."+nl+ch+"5");
        tstr=Integer.parseInt(in.next());
        exit(tstr<=0||tstr>5,4,"Invalid Input. Sync Move Level "+tstr+" not possible.");
        bp=(int)Math.floor(minfo[0]*((100+(5*(tstr-1)))/100.0));
        off=giveOffence(uid,smd,minfo[2],mvnam);
        if (minfo[1]==1)
        {
            GameText.println(nl+"This move can damage more than one opponents"
                        +nl+"How many opponents were on the field when the move was used?");
            tstr=Integer.parseInt(in.next());
            exit(!(tstr>=1&&tstr<=3),5,"Invalid Input. "+tstr+" opponents not possible.");
            field[0][0]=tstr;
        }
        if (field[0][0]!=1)
            for (i=0; i<field[0][0]; i++)
            {
                field[i][0]=tstr;
                GameText.println(nl+"Was the move super-effective on the "+((i==0)?"first":((i==1)?"second":"third"))+" opponent? "+yn);
                if (in.next().toLowerCase().charAt(0)=='y')
                    field[i][1]=2;
                GameText.println(nl+"Was the move a critical hit on the "+((i==0)?"first":((i==1)?"second":"third"))+" opponent? "+yn);
                if (in.next().toLowerCase().charAt(0)=='y')
                    field[i][2]=2;
                def[i]=giveDefense(i, field[i][2], minfo[2]);
            }
        else
        {
            GameText.println(nl+"Was the move super-effective on the opponent? "+yn);
            if (in.next().toLowerCase().charAt(0)=='y')
                field[0][1]=2;
            GameText.println(nl+"Was the move a critical hit on the opponent? "+yn);
            if (in.next().toLowerCase().charAt(0)=='y')
                field[0][2]=2;
            def[0]=giveDefense(3, field[0][2], minfo[2]);
        }
        bp=sideEffects(bp,mvnam);
        GameText.println("Are there any passive skills affecting the damage of the move? "+yn);
        if (in.next().toLowerCase().charAt(0)=='y')
            passiveCalc();
        if (minfo[1]==1)
            for (i=0; i<field[0][0]; i++)
            {
                mod=((field[i][0]==2)?2.0/3.0:((field[i][0]==3)?0.5:1))*field[i][1]*((field[i][2]==2)?1.5:1);
                roll=((int)(Math.random()*10)+90)/100;
                dmg=(long)Math.floor((bp*(off/def[i])+1)*mod*roll);
                GameText.println("Damage on the "+((i==0)?"first":((i==1)?"second":"third"))+" opponent = "+dmg);
            }
        else
        {
            mod=field[0][1]*((field[0][2]==2)?1.5:1);
            roll=(Math.random()+90)/100.0;
            dmg=(long)Math.floor((bp*(off/def[0])+1)*roll);
            GameText.println(nl+"Damage on the opponent = "+dmg);
        }
        in.close();
    }
    public static String returnSP(int uid)
    {
        String sp=" and "+pkmns[uid%1000-1],trnr=trnrs[uid/1000];
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
    public static int sideEffects(int bp, String mvnam)
    {
        Scanner in = new Scanner(System.in);
        int tstr=0;
        if (mvnam.equals("Avalanche"))
        {
            GameText.println("Was the user attacked while it was ready to counterattack? "+yn);
            if (in.next().toLowerCase().charAt(0)=='y')
                bp*=2;
        }
        if (mvnam.equals("Brine"))
        {
            GameText.println("Was the target's HP half or below? "+yn);
            if (in.next().toLowerCase().charAt(0)=='y')
                bp*=2;
        }
        if (mvnam.equals("Eruption"))
        {
            GameText.println("Please choose the range in which the user's HP lies."
                         +nl+"1. 100%"+nl+"2. 50-99%"+nl+"3. 33-50&"+nl+"4. 0-33%"
                         +nl+"Please give your choice in an integer in a range of 1-4");
            tstr=Integer.parseInt(in.next());
            exit(tstr<1||tstr>4,31,"Invalid Input. There is no HP range at that place.");
        }
        if (mvnam.equals("Facade"))
        {
            checkStatus(10110,0);
            bp*=(Array.sumAll(stts[1])==1)?2:1;
        }
        if (mvnam.equals("Fury Cutter"))
        {
            GameText.println("How many times Fury Cutter was used before using this move?");
            tstr=Integer.parseInt(in.next());
            bp*=Math.pow(2,(tstr<=0)?0:((tstr>=3)?3:tstr));
        }
        if (mvnam.equals("Gyro Ball"))
        {
            if (buffs[1][4]==7)
            {
                GameText.println("Please give any in-battle buffs/debuffs to the Speed stat of the target."
                             +nl+"Pease give your choice in an integer in a range of (-6)-6");
                tstr=Integer.parseInt(in.next());
                exit(tstr<-6||tstr>6,12,"Invalid Input. "+((tstr<0)?"":"+")+tstr+" Speed buff not possible.");
                buffs[1][4]=tstr;
            }
            bp*=(buffs[1][4]>0)?2:1;
        }
        if (mvnam.equals("Hex"))
        {
            checkStatus(11111,1);
            bp*=(Array.sumAll(stts[1])==1)?2:1;
        }
        if (mvnam.equals("Mirror Coat"))
        {
        }
        if (mvnam.equals("Stored Power"))
        {
            for (int i=0; i<7; i++)
                if (buffs[0][i]==7&&customAdder(buffs[0])<12)
                    checkBuffs(i,0);
            bp*=((customAdder(buffs[0])>12)?12:customAdder(buffs[0]))+1;
        }
        if (mvnam.equals("Venoshock"))
        {
            checkStatus(00010,1);
            bp*=stts[1][3]+1;
        }
        return bp;
    }
    public static void passiveCalc()
    {
        Scanner in = new Scanner(System.in);
        int passid=0,passint=0;
        boolean psvc=true;
        String pass="";
        while(psvc)
        {
            GameText.println("Please give the full name of the passive skill that applies.");
            pass=in.nextLine();
            if (pass.length()>2)
                if (Char.toInt(pass.charAt(pass.length()-1))>0&&!passives[Array.returnID(passives,pass.substring(0,pass.length()-2))].equals("NA"))
                {
                    passint=Char.toInt(pass.charAt(pass.length()-1));
                    pass=pass.substring(0,pass.length()-2);
                    passid=Array.returnID(passives,pass);
                    if (passid==0)
                    {
                        GameText.println("OK, Added "+pass+" "+passint);
                        psv+=passint/10;
                    }
                }
                else
                    psvc=false;
            else
                psvc=false;
        }
    }
    public static double giveOffence(int uid, int smd, int cat, String mvnam)
    {
        Scanner in = new Scanner(System.in);
        int bof,gear,grid,buff,smb;
        String nl="\0\0\0\n",ch="Give your choice in an integer number in a range of ";
        GameText.println(nl+"Please give the "+((cat==1)?"Special ":"")+"Attack stat of the pair.");
        bof=Integer.parseInt(in.next());
        exit(bof<1,10, "Invalid Input. "+bof+" "+((cat==1)?"Special ":"")+"Attack stat not possible.");
        GameText.println(nl+"Please give the additions to the "+((cat==1)?"Special ":"")+"Attack stat of the pair due to gear."
                     +nl+ch+"3-40");
        gear=Integer.parseInt(in.next());
        exit((gear<3||gear>40)&&gear!=0,11, "Invalid Input. No Gear can give "+((gear<0)?"":"+")+gear+" "+((cat==1)?"Special ":"")+"Attack stat.");
        GameText.println(nl+"Please give the additions to the "+((cat==1)?"Special ":"")+"Attack stat of the pair due to the Sync Grid."
                     +nl+"Please give your choice in multiples of 5, such as 5, 10, 15, 20 etc."
                     +nl+"Note: Any value which is not a multiple of 5 will be rounded down to the nearest multiple of 5");
        grid=Integer.parseInt(in.next());
        exit(grid<0,12, "Invalid Input. The Sync Grid cannot give "+gear+" "+((cat==1)?"Special ":"")+"Attack stat.");
        grid-=grid%5;
        checkBuffs(cat,0);
        buff=buffs[0][cat];
        GameText.println(nl+"How many sync moves were used by the attacker before using this move?");
        smb=Integer.parseInt(in.next());
        exit(smb<0,13, "Invalid Input.");
        exit(smd==1&&smb==0,14,"Invalid Input. The Sync pair \""+returnSP(uid)+"\" cannot use "+mvnam+" before using atleast one sync move itself.");
        return Math.floor((bof+gear+grid)*((buff==0)?1:((buff==1)?1.25:1+(buff+2.0)/10.0)))*(1.0+smb/2.0);
    }
    public static double giveDefense(int i, int crit, int cat)
    {
        Scanner in = new Scanner(System.in);
        int def,buff=0,smb=0;
        String nl="\0\0\0\n";
        GameText.println(nl+"Please give the "+((cat==1)?"Special ":"")+"Defense stat of the"+
                         ((i==0)?"first":((i==1)?"second":((i==2)?"third":"")))+" opponent.");
        def=Integer.parseInt(in.next());
        exit(def<1,20, "Invalid Input. "+def+" "+((cat==1)?"Special ":"")+"Defense stat not possible.");
        if (crit==0)
        {
            checkBuffs(cat+2,i+1);
            buff=buffs[(i+1==4)?1:i][cat+2];
            GameText.println(nl+"How many sync moves were used by the opponent side before using this move?");
            smb=Integer.parseInt(in.next());
            exit(smb<0,22,"Invalid Input.");
        }
        return Math.floor(def*((buff>0)?((buff==1)?1.25:1+(buff+2.0)/10.0):((buff==0)?1:((buff==1)?0.75:1+(buff-1.0)/10.0))))*(1.0+smb/2.0);
    }
    public static void checkStatus(int sttscd, int trgt)
    {
        Scanner in = new Scanner(System.in);
        int tstr,j=0,i;
        int[] sttses = Array.stringToIntArray(Integer.toString(sttscd).split("")),stts_indx = new int[Array.sumAll(sttses)];
        String[] stts_lst = {"Burned","Frozen","Paralyzed","Poisoned or Badly Poisoned","Asleep"};
        for (i=0; i<5; i++)
            if (sttses[i]==1)
            {
                stts_indx[j]=i;
                j++;
            }
        if(stts[trgt][0]==2)
            if (stts_indx.length>1)
            {
                GameText.println("Was the "+((trgt==0)?"user":"target")+" inflicted by any status condition?");
                if (in.next().toLowerCase().charAt(0)=='y')
                {
                    GameText.println("With which status condition was the "+((trgt==0)?"user":"target")+" inflicted with?");
                    for (i=0; i<stts_indx.length; i++)
                        GameText.println((i+1)+". "+stts_lst[stts_indx[i]]);
                    GameText.println("Please give your choice in an integer in a range of 1-"+stts_indx.length);
                    tstr=Integer.parseInt(in.next());
                    exit(tstr<1||tstr>stts_indx.length,31,"Invalid Input. There is no status condition at that place.");
                    stts[trgt]=Array.setAll(stts[1],0);
                    stts[trgt][tstr-1]=1;
                } else
                    stts[trgt]=Array.setAll(stts[trgt],0);
            } else if (stts_indx.length==1)
            {
                GameText.println("Was the "+((trgt==0)?"user":"target")+" "+stts_lst[stts_indx[0]]+"?");
                if (in.next().toLowerCase().charAt(0)=='y')
                {
                    stts[trgt]=Array.setAll(stts[1],0);
                    stts[trgt][stts_indx[0]]=1;
                }
            }
    }
    public static void checkBuffs(int i, int trgt)
    {
        Scanner in = new Scanner(System.in);
        int tstr;
        String[] buff_lst={"Attack","Special Attack","Defense","Special Defense","Speed","Accuracy","Evasiveness"};
        if (buffs[(trgt==4)?1:trgt][i]==7)
        {
            GameText.println("Please give any in-battle buffs/debuffs to the "+buff_lst[i]+" stat of the "
                           +((trgt==0)?"user":((trgt==1)?"first":((trgt==2)?"second":((trgt==3)?"third":"")))+" target")+"."
                         +nl+"Pease give your choice in an integer in a range of (-6)-6");
            tstr=Integer.parseInt(in.next());
            exit(tstr<-6||tstr>6,11,"Invalid Input. "+((tstr<0)?"":"+")+tstr+" "+buff_lst[i]+" buff not possible.");
            buffs[(trgt==4)?1:trgt][i]=tstr;
        }
    }
    public static int[][] msets = {{141,142},{54,56},{88,137,60},{115,116},{116,113},{21,70},{142},{142},{46},{55,52},
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
    public static  int[][] tdabs = {{1001,1002,1003},{2004,10002005},{3006},{4007,4008},{5009},{6010,6011},{7012},{8013},{9014,9015},{10016,10017,10018},
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
    public static String[] pkmns = {"Pikachu","Torchic","Solgaleo","Onix","Tyranitar","Starmie","Voltorb","Electrode","Vileplume","Ponyta",
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
    public static String[] trnrs = {"MC","Main Character","Brock","Misty","Lt. Surge","Erika","Blaine","Lorelei","Bruno","Agatha",
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
    public static String[] smoves = {"Thunder of Newfound Passion","Fire Sync Impact","Shining Friendship Sunraze Smash","Rock-Solid Rockslide","Sygnature Rock-Solid Stone Edge","Tomboyish Mermaid Bubble Beam","Electric Sync Beam","Surging Charge Beam","Nature-Loving Petal Dance","Fire Sync Impact",
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
    public static String[] moves = {"Absorb","Accelerock","Acid","Aerial Ace","Air Cutter","Air Slash","Ancient Power","Aqua Jet","Aqua Tail","Astonish",
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
                      "Wrap","X-Scissor","Zen Headbutt","Zing Zap","Hydro Cannon","Frenzy Plant","Sludge","Lunge","Crush Claw","Multi-Attack",
                      "Moonblast","NA"};
    public static int[][] sminfos = {{250,1,4},{200,0,7},{250,0,17},{200,0,16},{250,0,16},{200,1,18},{200,1,4},{200,1,4},{200,1,10},{200,0,7},
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
    public static int[][] minfos = {{19,0,1,10},{75,0,0,16},{19,0,1,14},{45,0,1,8},{43,1,1,8},{45,0,1,8},{47,0,1,16},{75,0,0,18},{56,0,0,18},{12,0,0,9},
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
                     {39,0,0,13},{50,0,0,1},{51,0,0,15},{42,0,0,4},{178,0,1,18},{178,0,1,10},{42,0,1,14},{80,0,0,1},{63,0,0,13},{125,0,0,13},
                     {98,0,1,5}};
    public static void exit(boolean b, int en, String e)
    {
        if (b)
        {
            program.showError(en,e);
            program.terminate();
        }
    }
    public static int customAdder(int[] a)
    {
        int b=0;
        for (int i=0; i<a.length&&a[i]<7&&a[i]>0; i++)
            b+=a[i];
        return b;
    }
}