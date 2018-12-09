using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Job1
{
    class Rectangle:Point
    {
        private double height;//高

        public double Height
        {
            get { return height; }
            set { height = value; }
        }
        private double width;//宽

        public double Width
        {
            get { return width; }
            set { width = value; }
        }
        private Point pointRectangle;
        internal Point PointRectangle
        {
            get { return pointRectangle; }
            set { pointRectangle = value; }
        }

        private String name;
        public String Name
        {
            get { return name; }
            set { name = value; }
        }
        //继承父类的无参构造方法
        public Rectangle(double height, double width, Point pointRectangle,String name)
        {
            this.width = width;
            this.height = height;
            this.pointRectangle = pointRectangle;
            this.name = name;
        }
        public double Area() 
        {
            return this.width * this.height;
        }
        public String toString() 
        {
            return this.name;
        }
    }
}
