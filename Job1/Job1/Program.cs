using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Job1
{
    class Program
    {
        static void Sort() 
        {
        
        }
        static void Main(string[] args)
        {
            Point point = new Point(1.00, 2.00);
            Rectangle rectangle1 = new Rectangle(10.00, 10.00, point,"矩形A");
            Console.WriteLine("矩形" + rectangle1.toString() + "的坐标位置为：" + rectangle1.PointRectangle.Location());
            Console.WriteLine("矩形" + rectangle1.toString() + "的面积为：" + rectangle1.Area());
            Rectangle rectangle2 = new Rectangle(11.00, 9.00, point,"矩形B");
            Console.WriteLine("矩形" + rectangle2.toString() + "的坐标位置为：" + rectangle2.PointRectangle.Location());
            Console.WriteLine("矩形" + rectangle2.toString() + "的面积为：" + rectangle2.Area());
            Rectangle rectangle3 = new Rectangle(12.00, 12.00, point, "矩形C");
            Console.WriteLine("矩形" + rectangle3.toString() + "的坐标位置为：" + rectangle3.PointRectangle.Location());
            Console.WriteLine("矩形" + rectangle3.toString() + "的面积为：" + rectangle3.Area());
            Rectangle[] rectangleArray = { rectangle1, rectangle2, rectangle3 };
            for (int i = 0; i < rectangleArray.Length - 1; i++)
            {// 排序趟数（数组长度减一）
                for (int j = 0; j < rectangleArray.Length - i - 1; j++)
                { // 每一趟将最大的数沉淀到相对应的末尾
                    if (rectangleArray[j].Area() > rectangleArray[j + 1].Area())
                    {// 前一位数大于后一位数 即进行交换
                        Rectangle temp = rectangleArray[j];
                        rectangleArray[j] = rectangleArray[j + 1];
                        rectangleArray[j + 1] = temp;
                    }
                }
            }
            Console.Write("矩形面积从小到大排列为：");
            for (int i = 0; i < rectangleArray.Length; i++)
            {
                Console.Write(rectangleArray[i].toString() + "\t");
            }

            Console.WriteLine();
            Console.WriteLine("--------------------------");
            Circle circle1 = new Circle(11.00, point,"圆A");
            Console.WriteLine("圆"+circle1.toString()+"的坐标位置为：" + circle1.PointCircle.Location());
            Console.WriteLine("圆的面积为：" + circle1.Area());
            Circle circle2 = new Circle(9.00, point, "圆B");
            Console.WriteLine("圆" + circle2.toString() + "的坐标位置为：" + circle2.PointCircle.Location());
            Console.WriteLine("圆的面积为：" + circle2.Area());
            Circle circle3 = new Circle(10.00, point, "圆C");
            Console.WriteLine("圆" + circle3.toString() + "的坐标位置为：" + circle3.PointCircle.Location());
            Console.WriteLine("圆的面积为：" + circle3.Area());
            Circle[] circleArray = { circle1,circle2,circle3};
            for (int i = 0; i < circleArray.Length - 1; i++)
            {// 排序趟数（数组长度减一）
                for (int j = 0; j < circleArray.Length - i - 1; j++)
                { // 每一趟将最大的数沉淀到相对应的末尾
                    if (circleArray[j].Area() > circleArray[j + 1].Area())
                    {// 前一位数大于后一位数 即进行交换
                        Circle temp = circleArray[j];
                        circleArray[j] = circleArray[j + 1];
                        circleArray[j + 1] = temp;
                    }
                }
            }
            Console.Write("圆面积从小到大排列为：");
            for (int i = 0; i < circleArray.Length; i++)
            {
                Console.Write(circleArray[i].toString() + "\t");
            }
            Console.ReadLine();
        }
    }
}
