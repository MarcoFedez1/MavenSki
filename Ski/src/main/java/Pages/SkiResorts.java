package Pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SkiResorts {
	WebDriver driver;
	
	public SkiResorts (WebDriver driver){
        this.driver = driver;
        //This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
    }
	
	@FindBy (xpath = "//ul[@class='nav nav-list']/child::li/child::a")
	private List<WebElement> Resorts;
	
	
	public List<WebElement> SkiResort() {
		return Resorts;
	}
	public static String [] Resorts() {
		String [] resorts =  { 
				"Alpe D Huez",
				"Alta",
				"Alta Badia",
				"Andorra",
				"Aspen",
				"Banff/Lake Louise",
				"Bariloche",
				"Beaver Creek",
				"Big Sky Resort",
				"Big White",
				"Bormio",
				"Brides les Bains",
				"Cervinia",
				"Chamonix",
				"Champoluc",
				"Copper Mountain",
				"Cortina",
				"Courchevel",
				"Courmayeur",
				"Crans Montana",
				"Crested Butte",
				"Davos",
				"Deer Valley Resort",
				"Durango Mountain Resort",
				"Engelberg-Titlis",
				"Fernie",
				"Furano",
				"Grand Targhee",
				"Grindelwald",
				"Gstaad",
				"Hakuba",
				"Heavenly",
				"Innsbruck",
				"Jackson Hole",
				"Jay Peak",
				"Keystone",
				"Kicking Horse",
				"Killington",
				"Kimberley",
				"Kirkwood",
				"Kiroro",
				"Kitzbuhel",
				"Klosters",
				"La Thuile",
				"Lake Placid",
				"Las Lenas",
				"Lech",
				"Les Menuires",
				"Livigno",
				"Loon",
				"Madonna di Campiglio",
				"Mammoth",
				"Megeve",
				"Meribel",
				"Mont-Tremblant",
				"Mount Snow",
				"Mt. Bachelor",
				"New York Resorts",
				"Niseko",
				"Northstar at Tahoe",
				"Okemo",
				"OREGON RESORTS",
				"Panorama",
				"Park City Mountain Resort",
				"Portillo",
				"Red Mountain",
				"Reno",
				"Revelstoke",
				"Rusutsu",
				"Saas Fee",
				"Salt Lake City",
				"Schweitzer Mountain Resort",
 				"Silver Star",
				"Snowbird",
				"Snowmass",
				"Solitude Resort",
				"Squaw Valley",
				"St. Anton",
				"St. Moritz",
				"Steamboat Springs",
				"Stowe",
				"Summit County",
				"Sun Peaks",
				"Sun Valley",
				"Sundance",
				"Sunday River",
				"Taos",
				"Telluride",
				"Termas de Chillan",
				"Tignes",
				"Vail",
				"Val D Isere",
				"Val Di Fassa",
				"Val Gardena",
				"Val Thorens",
				"Valle Nevado",
				"Verbier",
				"Wengen",
				"Whistler Blackcomb",
				"Whitefish Mountain Resort",
				"Whitewater",
				"Winter Park",
				"Zermatt",
				"Zurs"
			};
		
		return resorts;
	}
	
	//"Breckenridge"
	//"Garmisch",
	public static String [] Resorts2() {
		
		String [] resorts =  { 
				"Alyeska",
				"Andermatt",
				"Arabba Marmolada",
				"Arosa",
				"Avoriaz",
				"Bad Gastein",
				"Corralco",
				"Flims",
				"Igls",
				"Interlaken",
				"Madesimo", 
				"Mont Sainte Anne",
				"Morzine",
				"Sauze d'Oulx",
				"Sestriere",
				"Solden",
				"Zell am See"
				};
			return resorts;	
		}

}
