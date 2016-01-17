using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Formatters.Binary;
using System.Text;
using System.Threading.Tasks;
using ToyLanguage.Exceptions;
using ToyLanguage.Interfaces;
using ToyLanguage.Model;
using ToyLanguage.Model.Collections;

namespace ToyLanguage
{
    class Repository: IRepository
    {
        /**
    * The list of current program states
    * For now only one item will be there
    */
        private List<ProgramState> programStateList;

        /**
         * The constructor
         */
        public Repository()
        {
            programStateList = new List<ProgramState>();
        }

        public void createProgram(IMyStack<IMyStatement> mExecutionStack, IMyDictionary<String, int> myDictionary, IMyList<String> mOutput, IHeap<int, int> heap, IMyStatement mInitialProgram)
        {
            programStateList.Add(new ProgramState(mExecutionStack, myDictionary, mOutput, heap, mInitialProgram));
        }

    public void setProgramStateList(List<ProgramState> list)
        {
            programStateList = list;
        }

      
    public List<ProgramState> getProgramStateList()
        {
            return programStateList;
        }

        /**
         * Get the latest program state
         * @return The ProgramState
         */

        public ProgramState getCurrentState()
        {
            if(programStateList.Count() == 0)
            {
                throw new EmptyContainerException();
            }
            return programStateList.ElementAt(programStateList.Count() - 1);
        }

        public void Serialize()
        {
            IFormatter formatter = new BinaryFormatter();
            System.IO.Stream stream = new FileStream("MyFile.bin", FileMode.Create, FileAccess.Write, FileShare.None);
            formatter.Serialize(stream, programStateList.ElementAt(programStateList.Count-1));
            stream.Close();
        }

        public void DeSerialize()
        {
            IFormatter formatter = new BinaryFormatter();
            Stream stream = new FileStream("Serialization.bin", FileMode.Open, FileAccess.Read, FileShare.Read);
            ProgramState serializeProgramState = (ProgramState)formatter.Deserialize(stream);

            //replace old program with the new one
            if (programStateList.Count > 0)
            {
                programStateList.RemoveAt(programStateList.Count - 1);
            }
            
            programStateList.Add(serializeProgramState);
            stream.Close();
        }

        public void SaveStateInFile()
        {
            ProgramState programState = programStateList.ElementAt(programStateList.Count - 1);
            // Write the string to a file.
            System.IO.StreamWriter file = new System.IO.StreamWriter("SaveInFile.txt");
            file.WriteLine("STACK");
            file.WriteLine(programState.getExecutionStack().ToString());
            file.WriteLine("Dictionary:");
            file.WriteLine(programState.getMyDictionary().ToString());
            file.WriteLine("Output:");
            file.WriteLine(programState.getOutput().ToString());
            file.Close();
        }

       
    }
}
