class Tile
{
  int x, y;
  color colour;
  
  boolean spread;
  
  Tile(int x, int y, int colour)
  {
    this.x = x;
    this.y = y;
    this.colour = colour;
    
    spread = false;
  }
  
  void Display()
  { 
    fill(colour);
    
    if (!show) noStroke();
    else stroke(180);
    
    rect(x * 23, y * 20, 20, 20);
  }
}
Tile grid[][] = new Tile[35][35];

color colours[] = {
  color(165, 42, 42),
  color(255, 0, 0),
  color(255, 165, 0),
  color(255, 255, 0),
  color(0, 150, 0),
  color(0, 0, 255),
  color(255, 0, 200),
  color(0),
  color(255),
  color(225, 158, 25),
  color(225, 25, 205),
  color(25, 38, 225),
  color(102, 0, 153),
  color(142, 229, 238)
};

color selected = color(255);
boolean show = true;

char confirm = 'a';

void setup()
{
  size(500, 720);
  
  for (int a = 0; a < grid.length; ++a)
  {
    for (int b = 0; b < grid[a].length; ++b)
      grid[a][b] = new Tile(a, b, color(255));
  }
}

void draw()
{ 
  for (int a = 0; a < grid.length; ++a)
  {
    for (int b = 0; b < grid[a].length; ++b)
      grid[b][a].Display();
  }
  
  if (show)
  {
    for (int a = 0; a < colours.length; ++a)
    {
      fill(colours[a]);
      stroke(0);
      ellipse(50, (a * 50) + 50, 30, 30);
    }
  }
  
  if (mousePressed && mouseButton == LEFT)
  {
    for (int a = 0; a < grid.length; ++a)
    {
      for (int b = 0; b < grid[a].length; ++b)
      {
        if (mouseX >= (b * 20) && mouseX <= 20 + (b * 20) && mouseY >= (a * 20) && mouseY <= 20 + (a * 20))
        {
          if (show)
          {
            for (int c = 0; c < colours.length; ++c)
            {
              if (dist(mouseX, mouseY, 50, (c * 50) + 50) <= 30)
                return;
            }
          }
          
          grid[b][a].colour = selected;
        }
      }
    }
  }
  else if (mousePressed && mouseButton == RIGHT)
  {
    color start = color(1);
    for (int a = 0; a < grid.length; ++a)
    {
      for (int b = 0; b < grid[a].length; ++b)
      {
        if (mouseX >= (b * 20) && mouseX <= 20 + (b * 20) && mouseY >= (a * 20) && mouseY <= 20 + (a * 20))
        {
          start = grid[b][a].colour;
           
          grid[b][a].colour = selected;
          grid[b][a].spread = true;
        }
      }
    }
    
    if (start == color(1))
      return;
    
    for (int a = 0; a < grid.length; ++a)
    {
      for (int b = 0; b < grid[a].length; ++b)
      {
        if (grid[b][a].spread)
        {
          if (a > 0 && grid[b][a - 1].colour == start)
          {
            grid[b][a - 1].colour = grid[b][a].colour;
            grid[b][a - 1].spread = true;
          }
            
          if (a < grid.length - 1 && grid[b][a + 1].colour == start)
          {
            grid[b][a + 1].colour = grid[b][a].colour;
            grid[b][a + 1].spread = true;
          }
          
          if (b > 0 && grid[b - 1][a].colour == start)
          {
            grid[b - 1][a].colour = grid[b][a].colour;
            grid[b - 1][a].spread = true;
          }
            
          if (b < grid[a].length - 1 && grid[b + 1][a].colour == start)
          {
            grid[b + 1][a].colour = grid[b][a].colour;
            grid[b + 1][a].spread = true;
          }
          
          grid[b][a].spread = false;
        }
      }
    }
    
    for (int a = grid.length - 1; a > 0; --a)
    {
      for (int b = grid[a].length - 1; b >= 0; --b)
      {
        if (grid[b][a].spread)
        {
          if (a > 0 && grid[b][a - 1].colour == start)
          {
            grid[b][a - 1].colour = grid[b][a].colour;
            grid[b][a - 1].spread = true;
          }
            
          if (a < grid.length - 1 && grid[b][a + 1].colour == start)
          {
            grid[b][a + 1].colour = grid[b][a].colour;
            grid[b][a + 1].spread = true;
          }
          
          if (b > 0 && grid[b - 1][a].colour == start)
          {
            grid[b - 1][a].colour = grid[b][a].colour;
            grid[b - 1][a].spread = true;
          }
            
          if (b < grid[a].length - 1 && grid[b + 1][a].colour == start)
          {
            grid[b + 1][a].colour = grid[b][a].colour;
            grid[b + 1][a].spread = true;
          }
          
          grid[b][a].spread = false;
        }
      }
    }
    
    for (int a = 0; a < grid.length; ++a)
    {
      for (int b = 0; b < grid[a].length; ++b)
      {
        grid[b][a].spread = false;
      }
    }
  }
}

void mousePressed()
{
  if (show)
  {
    for (int a = 0; a < colours.length; ++a)
    {
      if (dist(mouseX, mouseY, 50, (a * 50) + 50) <= 30)
        selected = colours[a];
    }
  }
}

void keyPressed()
{
  if (key == 'c' && confirm == 'c')
  {
    for (int a = 0; a < grid.length; ++a)
    {
      for (int b = 0; b < grid[a].length; ++b)
        grid[b][a].colour = color(255);
    }
    
    confirm = 'a';
  }
  else if (key == 'c' && confirm != 'c')
  {
    confirm = 'c';
  }
  else
  {
    confirm = 'a';
  }
  
  if (key == ' ')
    show = !show;
}
