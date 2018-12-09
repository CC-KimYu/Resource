using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Calculator
{
    public partial class Form1 : Form
    {
        private Boolean flag = false; //false表示一次运算未完成 true表示一次运算结束
        public Form1()
        {
            InitializeComponent();
        }
        private void AppendNumber(int number)
        {
            if (!flag)
            {
                textBox1.Text = textBox1.Text + number.ToString();
            }
            else {
                textBox1.Text = "";
                textBox1.Text = textBox1.Text + number.ToString();
                flag = false;
            }
        }

        private void button0_Click(object sender, EventArgs e)
        {
            AppendNumber(0);
        }

        private void button1_Click(object sender, EventArgs e)
        {
            AppendNumber(1);
        }

        private void button2_Click(object sender, EventArgs e)
        {
            AppendNumber(2);
        }

        private void button3_Click(object sender, EventArgs e)
        {
            AppendNumber(3);
        }

        private void button4_Click(object sender, EventArgs e)
        {
            AppendNumber(4);
        }

        private void button5_Click(object sender, EventArgs e)
        {
            AppendNumber(5);
        }

        private void button6_Click(object sender, EventArgs e)
        {
            AppendNumber(6);
        }

        private void button7_Click(object sender, EventArgs e)
        {
            AppendNumber(7);
        }

        private void button8_Click(object sender, EventArgs e)
        {
            AppendNumber(8);
        }

        private void button9_Click(object sender, EventArgs e)
        {
            AppendNumber(9);
        }

        private void buttonZero_Click(object sender, EventArgs e)   //归零 
        {
            textBox1.Text = "0";
        }

        private void buttonEqual_Click(object sender, EventArgs e)
        {
            flag = true;  //一次运算操作结束
            if (Regex.IsMatch(textBox1.Text.Substring(textBox1.Text.Length - 1, 1), @"[0-9]+$")) //表达式的最后一个字符为数字，表达式才合法
            {
                var a = new DataTable().Compute(textBox1.Text,null); //此处DataTable相当于一个栈进行维护表达式，并输出运算结果
                Console.WriteLine(a);
            textBox1.Text = a.ToString();
            }
            //double reasult = 0;//计算结果
            //string symbol = "";//操作符
            //char[] array1 = textBox1.Text.ToCharArray();
            //foreach (char cc in array1)
            //{
            //    if (cc == '+' || cc == '-' || cc == '*' || cc == '/')
            //    {
            //        symbol = cc.ToString();
            //    }
            //}
            //if (!symbol.Equals(""))
            //{
            //    char[] temp = symbol.ToCharArray();
            //    string[] array2 = textBox1.Text.Split(temp[0]);
            //    double operate1 = Convert.ToDouble(array2[0]);//操作数1
            //    double operate2 = Convert.ToDouble(array2[1]);//操作数2
            //    Console.WriteLine("操作数1为：" + operate1);
            //    Console.WriteLine("操作数2为：" + operate2);
            //    switch (symbol)
            //    {
            //        case "+":
            //            reasult = operate1 + operate2; break;
            //        case "-":
            //            reasult = operate1 - operate2; break;
            //        case "*":
            //            reasult = operate1 * operate2; break;
            //        case "/":
            //            reasult = operate1 / operate2; break;
            //    }
            //    textBox1.Text = reasult.ToString();
            //}
        }

        private void buttonPlus_Click(object sender, EventArgs e)
        {
            if(textBox1.Text!=""){
                if (Regex.IsMatch(textBox1.Text.Substring(textBox1.Text.Length - 1, 1), @"[0-9]+$"))  //判断文本框的最后一个元素是否为数字  数字即可输入操作符，否则不可输入操作符
                {
                    textBox1.Text = textBox1.Text + "+";
                    flag = false;
                }
            }
        }

        private void buttonLess_Click(object sender, EventArgs e)
        {
            if (textBox1.Text != "")
            {
                if (Regex.IsMatch(textBox1.Text.Substring(textBox1.Text.Length - 1, 1), @"[0-9]+$"))  //判断文本框的最后一个元素是否为数字  数字即可输入操作符，否则不可输入操作符
                {
                    textBox1.Text = textBox1.Text + "-";
                    flag = false;
                }
            }
        }

        private void buttonMultiply_Click(object sender, EventArgs e)
        {
            if (textBox1.Text != "")
            {
                if (Regex.IsMatch(textBox1.Text.Substring(textBox1.Text.Length - 1, 1), @"[0-9]+$"))  //判断文本框的最后一个元素是否为数字  数字即可输入操作符，否则不可输入操作符
                {
                    textBox1.Text = textBox1.Text + "*";
                    flag = false;
                }
            }
        }

        private void buttonExcept_Click(object sender, EventArgs e)
        {
            if (textBox1.Text != "")
            {
                if (Regex.IsMatch(textBox1.Text.Substring(textBox1.Text.Length - 1, 1), @"[0-9]+$"))  //判断文本框的最后一个元素是否为数字  数字即可输入操作符，否则不可输入操作符
                {
                    textBox1.Text = textBox1.Text + "/";
                    flag = false;
                }
            }
        }
    }
}
