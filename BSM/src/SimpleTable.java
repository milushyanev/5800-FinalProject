import javax.swing.table.DefaultTableModel;
public class SimpleTable extends DefaultTableModel {

     
	private static final long serialVersionUID = 1L;

		public void addRow(String[] row) {
            super.addRow(row);
            this.fireTableDataChanged();
     
        }
        
        public void deleteRow(int index) {
        	super.removeRow(index);
        	this.fireTableDataChanged();
        }
        
        public void editRow(String[] row, int index) {
        	super.insertRow(index, row);
        	this.fireTableDataChanged();
        }

      
    }  