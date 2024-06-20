import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;
//======================================================Don't modify below===============================================================//
enum PieceType {king, queen, bishop, knight, rook, pawn, none}
enum PlayerColor {black, white, none}
	
public class ChessBoard {
	private final JPanel gui = new JPanel(new BorderLayout(3, 3));
	private JPanel chessBoard;
	private JButton[][] chessBoardSquares = new JButton[8][8];
	private Piece[][] chessBoardStatus = new Piece[8][8];
	private ImageIcon[] pieceImage_b = new ImageIcon[7];
	private ImageIcon[] pieceImage_w = new ImageIcon[7];
	private JLabel message = new JLabel("Enter Reset to Start");

	ChessBoard(){
		initPieceImages();
		initBoardStatus();
		initializeGui();
	}
	
	public final void initBoardStatus(){
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++) chessBoardStatus[j][i] = new Piece();
		}
	}
	
	public final void initPieceImages(){
		pieceImage_b[0] = new ImageIcon(new ImageIcon("C:\\Users\\gyk23\\github\\2024_CP_Project\\cp_2024_final_project_bundle-4\\cp_2024_final_project_bundle\\img\\king_b.png").getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH));
		pieceImage_b[1] = new ImageIcon(new ImageIcon("C:\\Users\\gyk23\\github\\2024_CP_Project\\cp_2024_final_project_bundle-4\\cp_2024_final_project_bundle\\img\\queen_b.png").getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH));
		pieceImage_b[2] = new ImageIcon(new ImageIcon("C:\\Users\\gyk23\\github\\2024_CP_Project\\cp_2024_final_project_bundle-4\\cp_2024_final_project_bundle\\img\\bishop_b.png").getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH));
		pieceImage_b[3] = new ImageIcon(new ImageIcon("C:\\Users\\gyk23\\github\\2024_CP_Project\\cp_2024_final_project_bundle-4\\cp_2024_final_project_bundle\\img\\knight_b.png").getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH));
		pieceImage_b[4] = new ImageIcon(new ImageIcon("C:\\Users\\gyk23\\github\\2024_CP_Project\\cp_2024_final_project_bundle-4\\cp_2024_final_project_bundle\\img\\rook_b.png").getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH));
		pieceImage_b[5] = new ImageIcon(new ImageIcon("C:\\Users\\gyk23\\github\\2024_CP_Project\\cp_2024_final_project_bundle-4\\cp_2024_final_project_bundle\\img\\pawn_b.png").getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH));
		pieceImage_b[6] = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
		
		pieceImage_w[0] = new ImageIcon(new ImageIcon("C:\\Users\\gyk23\\github\\2024_CP_Project\\cp_2024_final_project_bundle-4\\cp_2024_final_project_bundle\\img\\king_w.png").getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH));
		pieceImage_w[1] = new ImageIcon(new ImageIcon("C:\\Users\\gyk23\\github\\2024_CP_Project\\cp_2024_final_project_bundle-4\\cp_2024_final_project_bundle\\img\\queen_w.png").getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH));
		pieceImage_w[2] = new ImageIcon(new ImageIcon("C:\\Users\\gyk23\\github\\2024_CP_Project\\cp_2024_final_project_bundle-4\\cp_2024_final_project_bundle\\img\\bishop_w.png").getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH));
		pieceImage_w[3] = new ImageIcon(new ImageIcon("C:\\Users\\gyk23\\github\\2024_CP_Project\\cp_2024_final_project_bundle-4\\cp_2024_final_project_bundle\\img\\knight_w.png").getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH));
		pieceImage_w[4] = new ImageIcon(new ImageIcon("C:\\Users\\gyk23\\github\\2024_CP_Project\\cp_2024_final_project_bundle-4\\cp_2024_final_project_bundle\\img\\rook_w.png").getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH));
		pieceImage_w[5] = new ImageIcon(new ImageIcon("C:\\Users\\gyk23\\github\\2024_CP_Project\\cp_2024_final_project_bundle-4\\cp_2024_final_project_bundle\\img\\pawn_w.png").getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH));
		pieceImage_w[6] = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
	}
	
	public ImageIcon getImageIcon(Piece piece){
		if(piece.color.equals(PlayerColor.black)){
			if(piece.type.equals(PieceType.king)) return pieceImage_b[0];
			else if(piece.type.equals(PieceType.queen)) return pieceImage_b[1];
			else if(piece.type.equals(PieceType.bishop)) return pieceImage_b[2];
			else if(piece.type.equals(PieceType.knight)) return pieceImage_b[3];
			else if(piece.type.equals(PieceType.rook)) return pieceImage_b[4];
			else if(piece.type.equals(PieceType.pawn)) return pieceImage_b[5];
			else return pieceImage_b[6];
		}
		else if(piece.color.equals(PlayerColor.white)){
			if(piece.type.equals(PieceType.king)) return pieceImage_w[0];
			else if(piece.type.equals(PieceType.queen)) return pieceImage_w[1];
			else if(piece.type.equals(PieceType.bishop)) return pieceImage_w[2];
			else if(piece.type.equals(PieceType.knight)) return pieceImage_w[3];
			else if(piece.type.equals(PieceType.rook)) return pieceImage_w[4];
			else if(piece.type.equals(PieceType.pawn)) return pieceImage_w[5];
			else return pieceImage_w[6];
		}
		else return pieceImage_w[6];
	}

	public final void initializeGui(){
		gui.setBorder(new EmptyBorder(5, 5, 5, 5));
	    JToolBar tools = new JToolBar();
	    tools.setFloatable(false);
	    gui.add(tools, BorderLayout.PAGE_START);
	    JButton startButton = new JButton("Reset");
	    startButton.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		initiateBoard();
	    	}
	    });
	    
	    tools.add(startButton);
	    tools.addSeparator();
	    tools.add(message);

	    chessBoard = new JPanel(new GridLayout(0, 8));
	    chessBoard.setBorder(new LineBorder(Color.BLACK));
	    gui.add(chessBoard);
	    ImageIcon defaultIcon = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
	    Insets buttonMargin = new Insets(0,0,0,0);
	    for(int i=0; i<chessBoardSquares.length; i++) {
	        for (int j = 0; j < chessBoardSquares[i].length; j++) {
	        	JButton b = new JButton();
	        	b.addActionListener(new ButtonListener(i, j));
	            b.setMargin(buttonMargin);
	            b.setIcon(defaultIcon);
	            if((j % 2 == 1 && i % 2 == 1)|| (j % 2 == 0 && i % 2 == 0)) b.setBackground(Color.WHITE);
	            else b.setBackground(Color.gray);
	            b.setOpaque(true);
	            b.setBorderPainted(false);
	            chessBoardSquares[j][i] = b;
	        }
	    }

	    for (int i=0; i < 8; i++) {
	        for (int j=0; j < 8; j++) chessBoard.add(chessBoardSquares[j][i]);
	        
	    }
	}

	public final JComponent getGui() {
	    return gui;
	}
	
	public static void main(String[] args) {
	    Runnable r = new Runnable() {
	        @Override
	        public void run() {
	        	ChessBoard cb = new ChessBoard();
                JFrame f = new JFrame("Chess");
                f.add(cb.getGui());
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                f.setLocationByPlatform(true);
                f.setResizable(false);
                f.pack();
                f.setMinimumSize(f.getSize());
                f.setVisible(true);
            }
        };
        SwingUtilities.invokeLater(r);
	}
		
			//================================Utilize these functions========================================//	
	
	class Piece{
		PlayerColor color;
		PieceType type;
		
		Piece(){
			color = PlayerColor.none;
			type = PieceType.none;
		}
		Piece(PlayerColor color, PieceType type){
			this.color = color;
			this.type = type;
		}
	}
	
	public void setIcon(int x, int y, Piece piece){
		chessBoardSquares[y][x].setIcon(getImageIcon(piece));
		chessBoardStatus[y][x] = piece;
	}
	
	public Piece getIcon(int x, int y){
		return chessBoardStatus[y][x];
	}
	
	public void markPosition(int x, int y){
		chessBoardSquares[y][x].setBackground(Color.pink);
	}
	
	public void unmarkPosition(int x, int y){
		if((y % 2 == 1 && x % 2 == 1)|| (y % 2 == 0 && x % 2 == 0)) chessBoardSquares[y][x].setBackground(Color.WHITE);
		else chessBoardSquares[y][x].setBackground(Color.gray);
	}
	
	public void setStatus(String inpt){
		message.setText(inpt);
	}
	
	public void initiateBoard(){
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++) setIcon(i, j, new Piece());
		}
		setIcon(0, 0, new Piece(PlayerColor.black, PieceType.rook));
		setIcon(0, 1, new Piece(PlayerColor.black, PieceType.knight));
		setIcon(0, 2, new Piece(PlayerColor.black, PieceType.bishop));
		setIcon(0, 3, new Piece(PlayerColor.black, PieceType.queen));
		setIcon(0, 4, new Piece(PlayerColor.black, PieceType.king));
		setIcon(0, 5, new Piece(PlayerColor.black, PieceType.bishop));
		setIcon(0, 6, new Piece(PlayerColor.black, PieceType.knight));
		setIcon(0, 7, new Piece(PlayerColor.black, PieceType.rook));
		for(int i=0;i<8;i++){
			setIcon(1, i, new Piece(PlayerColor.black, PieceType.pawn));
			setIcon(6, i, new Piece(PlayerColor.white, PieceType.pawn));
		}
		setIcon(7, 0, new Piece(PlayerColor.white, PieceType.rook));
		setIcon(7, 1, new Piece(PlayerColor.white, PieceType.knight));
		setIcon(7, 2, new Piece(PlayerColor.white, PieceType.bishop));
		setIcon(7, 3, new Piece(PlayerColor.white, PieceType.queen));
		setIcon(7, 4, new Piece(PlayerColor.white, PieceType.king));
		setIcon(7, 5, new Piece(PlayerColor.white, PieceType.bishop));
		setIcon(7, 6, new Piece(PlayerColor.white, PieceType.knight));
		setIcon(7, 7, new Piece(PlayerColor.white, PieceType.rook));
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++) unmarkPosition(i, j);
		}
		onInitiateBoard();
	}
//======================================================Don't modify above==============================================================//	




//======================================================Implement below=================================================================//		
	enum MagicType {MARK, CHECK, CHECKMATE};
	private int selX, selY;
	private boolean check, checkmate, end;

	// 기물 종류별 클래스의 base class
	class Unit{
		Piece piece;
		int xpos;
		int ypos;
		Unit(){
			piece = null;
			xpos = -1;
			ypos = -1;
			System.out.println("Unexpected Unit Generating");
		}
		Unit(Piece p, int x, int y){
			piece = p;
			xpos = x;
			ypos = y;
		}
		// 기물의 예상 경로 validation 확인시 bound 설정
		static boolean isInBound(int x, int y){
			return ((0 <= x && x < 8) && (0 <= y && y < 8));
		}
		// {{attack 하지 않고 움직일 수 있는 좌표쌍}, {attack 할 수 있는 좌표쌍}}
		ArrayList<ArrayList<ArrayList<Integer>>> next(){
			return null;
		}
		// 클래스 내 x,y 좌표 변환 & 화면상 좌표 변환
		void move(int nextX, int nextY){
			setIcon(xpos, ypos, new Piece());
			xpos = nextX;
			ypos = nextY;
			setIcon(xpos, ypos, piece);
		}

		@Override
		public String toString() {
			return STR."Objcet at (\{xpos}, \{ypos})";
		}
	}

	// Pawn 클래스
	class Pawn extends Unit{
		Pawn(Piece piece, int x, int y){
			super(piece, x, y);
		}

		@Override
		public String toString() {
			return STR."\{piece.color} Pawn at (\{xpos}, \{ypos})";
		}
	}

	// Rook 클래스
	class Rook extends Unit{
		Rook(Piece piece, int x, int y){
			super(piece, x, y);
		}
		@Override
		public String toString() {
			return STR."\{piece.color} Rook at (\{xpos}, \{ypos})";
		}
	}

	// Knight 클래스
	class Knight extends Unit{
		Knight(Piece piece, int x, int y){
			super(piece, x, y);
		}
		@Override
		public String toString() {
			return STR."\{piece.color} Knight at (\{xpos}, \{ypos})";
		}
	}

	// Bishop 클래스
	class Bishop extends Unit{
		Bishop(Piece piece, int x, int y){
			super(piece, x, y);
		}
		@Override
		public String toString() {
			return STR."\{piece.color} Bishop at (\{xpos}, \{ypos})";
		}
	}

	// Queen 클래스
	class Queen extends Unit{
		Queen(Piece piece, int x, int y){
			super(piece, x, y);
		}
		@Override
		public String toString() {
			return STR."\{piece.color} Queen at (\{xpos}, \{ypos})";
		}
	}

	// King 클래스
	class King extends Unit{
		King(Piece piece, int x, int y){
			super(piece, x, y);
		}
		@Override
		public String toString() {
			return STR."\{piece.color} King at (\{xpos}, \{ypos})";
		}
	}

	// 흰색 기물들에 대한 정보를 저장
	private ArrayList<Unit> whiteUnit = new ArrayList<>();
	// 검은색 기물들에 대한 정보를 저장
	private ArrayList<Unit> blackUnit = new ArrayList<>();
	
	class ButtonListener implements ActionListener{
		int x;
		int y;
		ButtonListener(int x, int y){
			this.x = x;
			this.y = y;
		}
		public void actionPerformed(ActionEvent e) {	// Only modify here
			// (x, y) is where the click event occured
		}
	}
	
	void onInitiateBoard(){
		// 검은색 기물들에 대한 정보 저장
		for(int i = 0; i < 2; i++){
			for(int j = 0; j < 8; j++){
				switch (getIcon(i, j).type){
					case PieceType.pawn -> blackUnit.add(new Pawn(getIcon(i, j), i, j));
					case PieceType.rook -> blackUnit.add(new Rook(getIcon(i, j), i, j));
					case PieceType.knight -> blackUnit.add(new Knight(getIcon(i, j), i, j));
					case PieceType.bishop -> blackUnit.add(new Bishop(getIcon(i, j), i, j));
					case PieceType.queen -> blackUnit.add(new Queen(getIcon(i, j), i, j));
					case PieceType.king -> blackUnit.add(new King(getIcon(i, j), i, j));
				}
			}
		}
		for(int i = 6; i < 8; i++){
			for(int j = 0; j < 8; j++){
				switch (getIcon(i, j).type){
					case PieceType.pawn -> whiteUnit.add(new Pawn(getIcon(i, j), i, j));
					case PieceType.rook -> whiteUnit.add(new Rook(getIcon(i, j), i, j));
					case PieceType.knight -> whiteUnit.add(new Knight(getIcon(i, j), i, j));
					case PieceType.bishop -> whiteUnit.add(new Bishop(getIcon(i, j), i, j));
					case PieceType.queen -> whiteUnit.add(new Queen(getIcon(i, j), i, j));
					case PieceType.king -> whiteUnit.add(new King(getIcon(i, j), i, j));
				}
			}
		}
	}
}