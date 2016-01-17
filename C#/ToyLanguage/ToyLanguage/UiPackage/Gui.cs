using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Linq.Expressions;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using ToyLanguage.Interfaces;
using ToyLanguage.Model.Expressions;
using ToyLanguage.Model.Statements;

namespace ToyLanguage.UiPackage
{
    public partial class Gui : Form, Controller.PrintState
    {

        private Controller controller;

        private IRepository repository;


        public Gui()
        {
            repository = new Repository();
            controller = new Controller(repository);
            InitializeComponent();

            IMyStatement fork =
                           new CompoundStatement(new AssignStatement("v", new Model.Expressions.ConstantExpression(10)),
                                   new CompoundStatement(new HeapAllocation("a", new Model.Expressions.ConstantExpression(22)),
                                           new CompoundStatement(new ForkStatement(new WriteHeapStatement("a", new Model.Expressions.ConstantExpression(30))),
                                                   new CompoundStatement(new AssignStatement("v", new Model.Expressions.ConstantExpression(32)),
                                                           new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                                                   new CompoundStatement(new PrintStatement(new ReadHeap("a")),
                                                                            new CompoundStatement(new PrintStatement(new VariableExpression("v")), new PrintStatement(new ReadHeap("a")))))))));
            controller.createProgram(fork);
            controller.setListener(this);
        }

        private void Gui_Load(object sender, EventArgs e)
        {

        }

        private void button1_Click(object sender, EventArgs e)
        {
            controller.oneStepForAll();
        }

        private void textBox1_TextChanged(object sender, EventArgs e)
        {

        }

        private void button2_Click(object sender, EventArgs e)
        {
            controller.runAllSteps();
        }

        public void print(string message)
        {
            textBox1.AppendText(message + Environment.NewLine);
        }
    }
}
