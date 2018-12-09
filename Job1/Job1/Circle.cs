using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Job1
{
    class Circle:Point
    {
        private double radius;

        public double Radius
        {
            get { return radius; }
            set { radius = value; }
        }
        private Point pointCircle;

        internal Point PointCircle
        {
            get { return pointCircle; }
            set { pointCircle = value; }
        }
        private String name;

        public String Name
        {
            get { return name; }
            set { name = value; }
        }
        //继承父类的无参构造方法
        public Circle(double radius, Point pointCircle,String name) 
        {
            this.radius = radius;
            this.pointCircle = pointCircle;
            this.name = name;
        }
        public double Area()
        {
            return Math.PI * this.radius * this.radius;
        }
        public String toString()
        {
            return this.name;
        }
    }
}
