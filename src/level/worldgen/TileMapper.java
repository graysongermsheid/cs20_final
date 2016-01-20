package level.worldgen;

import level.Tile;
import java.util.Random;

public class TileMapper {

	private static Random r = new Random();
	
	public static Tile processTile(int x, int y, boolean[][] map){
		
		Tile t = new Tile(7);
		
		if (!map[y][x]){
			
			switch (r.nextInt(8)){
			
				case 0:
					t = new Tile(48);
					break;
				case 1:
					t = new Tile(50);
					break;
				default:
					t = new Tile(52);
					break;
			
			}

			return t;
		}
		
		t = new Tile(35);

		boolean nw = (x > 0 && y > 0) ? map[y - 1][x - 1] : true;                      //North-West neighbour
		boolean n  = (y > 0) ? map[y - 1][  x  ] : true;                               //North neighbour
		boolean ne = (x < map[0].length - 1 && y > 0) ? map[y - 1][x + 1] : true;          //North-East neighbour
		boolean w  = (x > 0) ? map[  y  ][x - 1] : true;                               //West neighbour
		boolean e  = (x < map[0].length - 1) ? map[  y  ][x + 1] : true;                   //East neighbour
		boolean se = (x < map[0].length - 1 && y < map.length - 1) ? map[y + 1][x + 1] : true;             //South-East neighbour
		boolean s  = (y < map.length - 1) ? map[y + 1][  x  ] : true;                      //South neighbour
		boolean sw = (x > 0 && y < map.length - 1) ? map[y + 1][x - 1] : true; //South-West neighbour

		//Tile tile = new Tile(90);

		if (!n & !w & !e & !s){
			
			t = new Tile(5);
			
		} else if (n & w & s & !se & e){
			
			t = new Tile(0);
			
		} else if (n & e & w & !s){
			
			if (!ne & !nw){
				
				t = new Tile(41);
				
			} else if (!ne){
				
				t = new Tile(29);
				
			} else if (!nw){
				
				t = new Tile(39);
				
			} else {
				
				t = new Tile(1);
				
			}
			
		} else if (n & w & s & !sw & e){
			
			t = new Tile(2);
			
		} else if (n & w & !e & s){
			
			if (!nw & !sw){
				
				t = new Tile(34);
				
			} else if (!nw){
				
				t = new Tile(37);
				
			} else if (!sw){
				
				t = new Tile(43);
				
			} else {
				
				t = new Tile(6);
				
			}
			
		} else if (n & !w & e & s){
			
			if (!ne & !se){
				
				t = new Tile(33);
				
			} else if (!ne){
				
				t = new Tile(36);
				
			} else if (!se){
				
				t = new Tile(42);
				
			} else {
				
				t = new Tile(8);
				
			}
			
		} else if (n & w & e & s & !ne){
			
			t = new Tile(12);
		
		} else if (!n & w & e & s){
			
			if (!sw & !se){
				
				t = new Tile(40);
				
			} else if (!sw){
				
				t = new Tile(23);
				
			} else if (!se){
				
				t = new Tile(38);
				
			} else {
				
				t = new Tile(13);
				
			}
			
		} else if (n & w & e & s & !nw){
			
			t = new Tile(14);
			
		} else if (!n & !w & e & s){
			
			if (!se){
				
				t = new Tile(44);
				
			} else {
				
				t = new Tile(3);
				
			}
			
		} else if (!n & w & !e & s){
			
			if (!sw){
				
				t = new Tile(45);
				
			} else {
				
				t = new Tile(4);
				
			}
			
		} else if (n & !w & e & !s){
			
			if (!ne){
				
				t = new Tile(46);
				
			} else {
				
				t = new Tile(9);
				
			}
			
		} else if (n & w & !e & !s){
			
			if (!nw){
				
				t = new Tile(47);
				
			} else {
				
				t = new Tile(10);
				
			}
			
		} else if (!n & !w & !e & s){
			
			t = new Tile(19);
			
		} else if (!n & !w & e & !s){
			
			t = new Tile(24);
			
		} else if (n & !w & !e & !s){
			
			t = new Tile(31);
			
		} else if (!n & w & !e & !s){
			
			t = new Tile(26);
			
		} else if (n & !w & !e & s){
			
			t = new Tile(17);
			
		} else if (!n & e & w & !s){
			
			t = new Tile(11);
			
		}

		if (n & w & s & e){

			if (!nw & !ne & !sw & !se){
			
				t = new Tile(25);
			
			} else if (!nw & !se & !sw){

				t = new Tile(21);

			} else if (!sw & !ne & !se){

				t = new Tile(22);

			} else if (!sw & !ne & !nw){

				t = new Tile(15);

			} else if (!nw & !se & !ne){

				t = new Tile(16);

			} else if (!nw & !ne){
			
				t = new Tile(20);
			
			} else if (!nw & !sw){
				
				t = new Tile(18);
			
			} else if (!sw & !se){
			
				t = new Tile(30);
			
			} else if (!ne & !se){
			
				t = new Tile(32);
			
			} else if (!ne & !sw){

				t = new Tile(27);

			} else if (!nw & !se){

				t = new Tile(28);

			}

		}
		
		return t;
		
	}
	
}
