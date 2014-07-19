import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFramework
{
	int loop = 1;
	static int DEPTH = 50;

	public static void main(String[] args) throws InterruptedException
	{
		Sensor device = new Sensor();
		Output panel = new Output(this);
		boolean drawing;

		while(loop == 1)
		{
			coords = device.getCoords();
			if(coords[2] >= DEPTH)
			{
				drawing = true;
			}
			else
			{
				drawing = false;
			}

			panel.draw(coords, draw);
			thread.sleep(100);
		}
	}

	public void quit()
	{
		loop = -1;
	}
}