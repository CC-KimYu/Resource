using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Job1
{
    class Point
    {
        private double x;//横坐标
        public double X
        {
            get { return x; }
            set { x = value; }
        }
        private double y;//纵坐标
        public double Y
        {
            get { return y; }
            set { y = value; }
        }
        public Point(double x, double y) 
        {
            this.x = x;
            this.y = y;
        }
        public Point() { }
        public String Location() 
        {
            return "("+this.x.ToString()+","+this.y.ToString()+")";
        }
    }
}
