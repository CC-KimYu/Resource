using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.Text.RegularExpressions;

namespace Job2
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }
        //判断计算式是否合法
        private bool CheckExpressionValid(string input)
        {
            string pattern = @"^(((?<o>\()[-+]?([0-9]+[-+*/])*)+[0-9]+((?<-o>\))([-+*/][0-9]+)*)+($|[-+*/]))*(?(o)(?!))$";
            //去掉空格，且添加括号便于进行匹配    
            return Regex.IsMatch("(" + input.Replace(" ", "") + ")", pattern);
        }
        private void userButton14_Click(object sender, EventArgs e)
        {
            if (CheckExpressionValid(richTextBox1.Text))//字符串合法
            {
                var result = new System.Data.DataTable().Compute(richTextBox1.Text, "");
                richTextBox1.Text += ("=" + result.ToString());
            }
            else 
            {
                MessageBox.Show("请输入合法的表达式");
            }
        }

        private void userButton0_Click(object sender, EventArgs e)
        {
            richTextBox1.Text += "0";
        }

        private void userButton1_Click(object sender, EventArgs e)
        {
            richTextBox1.Text += "1";
        }

        private void userButton2_Click(object sender, EventArgs e)
        {
            richTextBox1.Text += "2";
        }

        private void userButton3_Click(object sender, EventArgs e)
        {
            richTextBox1.Text += "3";
        }

        private void userButton4_Click(object sender, EventArgs e)
        {
            richTextBox1.Text += "4";
        }

        private void userButton5_Click(object sender, EventArgs e)
        {
            richTextBox1.Text += "5";
        }

        private void userButton6_Click(object sender, EventArgs e)
        {
            richTextBox1.Text += "6";
        }

        private void userButton7_Click(object sender, EventArgs e)
        {
            richTextBox1.Text += "7";
        }

        private void userButton8_Click(object sender, EventArgs e)
        {
            richTextBox1.Text += "8";
        }

        private void userButton9_Click(object sender, EventArgs e)
        {
            richTextBox1.Text += "9";
        }

        private void userButton10_Click(object sender, EventArgs e)
        {
            richTextBox1.Text += "+";
        }

        private void userButton11_Click(object sender, EventArgs e)
        {
            richTextBox1.Text += "-";
        }

        private void userButton12_Click(object sender, EventArgs e)
        {
            richTextBox1.Text += "*";
        }

        private void userButton13_Click(object sender, EventArgs e)
        {
            richTextBox1.Text += "/";
        }

        private void userButton15_Click(object sender, EventArgs e)
        {
            richTextBox1.Text = "";
        }

        private void userButton16_Click(object sender, EventArgs e)
        {
            if(richTextBox1.Text!="")
            {
                richTextBox1.Text = richTextBox1.Text.Substring(0, richTextBox1.Text.Length - 1);
            }
        }
    }
}
