using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using WeifenLuo.WinFormsUI.Docking;

namespace DockPanelExample
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            Form2 form2 = new Form2();
            Form3 form3 = new Form3();
            Form4 form4 = new Form4();
            Form5 form5 = new Form5();
            Form6 form6 = new Form6();
            Form7 form7 = new Form7();
            form2.Show(dockPanel1,DockState.Document);
            form3.Show(form2.Pane,DockAlignment.Left,0.20);
            form4.Show(form2.Pane,DockAlignment.Right,0.20);
            form5.Show(form3.Pane,DockAlignment.Bottom,0.30);
            form6.Show(form4.Pane, DockAlignment.Bottom, 0.30);
            form7.Show(form2.Pane, DockAlignment.Bottom, 0.30);
            //form2.DockAreas = DockAreas.DockTop;
            //form3.DockAreas = DockAreas.DockLeft;
            //form4.DockAreas = DockAreas.DockRight;
            //form5.DockAreas = DockAreas.DockBottom;
            //form6.DockAreas = DockAreas.Document;
            //form2.Show(dockPanel1);
            //form3.Show(dockPanel1);
            //form4.Show(dockPanel1);
            //form5.Show(dockPanel1);
            //form6.Show(dockPanel1);
            
        }
    }
}
