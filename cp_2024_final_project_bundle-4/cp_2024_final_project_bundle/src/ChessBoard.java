import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
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
		pieceImage_b[0] = new ImageIcon(new ImageIcon("./img/king_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_b[1] = new ImageIcon(new ImageIcon("./img/queen_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_b[2] = new ImageIcon(new ImageIcon("./img/bishop_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_b[3] = new ImageIcon(new ImageIcon("./img/knight_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_b[4] = new ImageIcon(new ImageIcon("./img/rook_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_b[5] = new ImageIcon(new ImageIcon("./img/pawn_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_b[6] = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));

		pieceImage_w[0] = new ImageIcon(new ImageIcon("./img/king_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_w[1] = new ImageIcon(new ImageIcon("./img/queen_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_w[2] = new ImageIcon(new ImageIcon("./img/bishop_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_w[3] = new ImageIcon(new ImageIcon("./img/knight_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_w[4] = new ImageIcon(new ImageIcon("./img/rook_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_w[5] = new ImageIcon(new ImageIcon("./img/pawn_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
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
	enum ClickAction {Attack, Move, None};
	private int selX, selY;
	private boolean check, checkmate, end;
	private PlayerColor turn = PlayerColor.none;
	Piece[][] mapGenerator(ArrayList<Unit> myUnits, ArrayList<Unit> enemyUnits){
		Piece[][] tempMap = new Piece[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				tempMap[i][j] = new Piece();
			}
		}

		for(Unit enemyUnit: enemyUnits){
			tempMap[enemyUnit.ypos][enemyUnit.xpos] = new Piece(enemyColor(), enemyUnit.piece.type);
		}
		for(Unit myUnit: myUnits){
			if(tempMap[myUnit.ypos][myUnit.xpos].color != PlayerColor.none)
				deleteUnit(enemyUnits, tempMap[myUnit.ypos][myUnit.xpos], myUnit.xpos, myUnit.ypos, false);
			tempMap[myUnit.ypos][myUnit.xpos] = new Piece(turn, myUnit.piece.type);
		}

		return tempMap;
	}

	ArrayList<Unit> unitBinder(PlayerColor pc, boolean reverse){
		ArrayList<Unit> target = new ArrayList<>();
		for(Unit item: (reverse) ? ((pc == PlayerColor.black) ? whiteUnit: blackUnit): ((pc == PlayerColor.white) ? whiteUnit: blackUnit)){
			switch (item.piece.type){
				case PieceType.pawn -> target.add(new Pawn(new Piece(item.piece.color, PieceType.pawn), item.xpos, item.ypos));
				case PieceType.rook -> target.add(new Rook(new Piece(item.piece.color, PieceType.rook), item.xpos, item.ypos));
				case PieceType.knight -> target.add(new Knight(new Piece(item.piece.color, PieceType.knight), item.xpos, item.ypos));
				case PieceType.bishop -> target.add(new Bishop(new Piece(item.piece.color, PieceType.bishop), item.xpos, item.ypos));
				case PieceType.queen -> target.add(new Queen(new Piece(item.piece.color, PieceType.queen), item.xpos, item.ypos));
				case PieceType.king -> target.add(new King(new Piece(item.piece.color, PieceType.king), item.xpos, item.ypos));
			}
		}
		return target;
	}

	// 체크 상태를 풀 수 있는지 확인
	boolean canRelieveCheck(int x, int y, PlayerColor pc, Unit selected, Unit king){
		ArrayList<Unit> myUnits = unitBinder(pc, false);
		ArrayList<Unit> enemyUnits = unitBinder(pc, true);

		Unit tempMyUnit= Unit.findUnit(myUnits, selected.piece, selected.xpos, selected.ypos);
		tempMyUnit.xpos = x;
		tempMyUnit.ypos = y;

		Piece[][] tempMap = mapGenerator(myUnits, enemyUnits);
		if(tempMyUnit.piece.type == PieceType.king)
			return !isTargeted(tempMyUnit.xpos, tempMyUnit.ypos, turn, tempMap, enemyUnits);
		return !isTargeted(king.xpos, king.ypos, turn, tempMap, enemyUnits);
	}

	// white - black 턴 전환
	void changeTurn(){
		switch (turn){
			case PlayerColor.none -> turn = PlayerColor.white;
			case PlayerColor.black -> turn = PlayerColor.white;
			case PlayerColor.white -> turn = PlayerColor.black;
		}

		String message = STR."\{turn.toString().toUpperCase()}'s turn";

		// 내 킹이 죽었으면 내가 진것
		Unit king = null;
		for(Unit unit: (turn == PlayerColor.white) ? whiteUnit: blackUnit){
			if(unit.piece.type == PieceType.king){
				king = unit;
			}
		}

		if(king == null){
			end = true;
			message = STR."end | \{enemyColor()} win";
		} else {
			// CHECK 상태인지 확인
			if(isTargeted(king.xpos, king.ypos, turn, chessBoardStatus, (turn == PlayerColor.black) ? whiteUnit: blackUnit)){
				check = true;
				message += " | CHECK";

				ArrayList<int[]> nextKing = new ArrayList<>();
				nextKing.addAll(king.next().get(0));
				nextKing.addAll(king.next().get(1));

				// 왕만 움직이는 것으로 체크가 풀리는 경우 확인
				boolean hasSimpleSolution = false;
				for(int[] nextKingPosition: nextKing){
					ArrayList<Unit> myUnits = unitBinder(turn, false);
					ArrayList<Unit> enemyUnits = unitBinder(turn, true);

					Unit tempKing = Unit.findUnit(myUnits, king.piece, king.xpos, king.ypos);
					tempKing.xpos = nextKingPosition[0];
					tempKing.ypos = nextKingPosition[1];

					Piece[][] tempMap = mapGenerator(myUnits, enemyUnits);
					if(!isTargeted(nextKingPosition[0], nextKingPosition[1], turn, tempMap, enemyUnits)){
						hasSimpleSolution = true;
						break;
					}
				}

				// 왕을 움직여도 체크가 풀리지 않는 경우 다른 기물로 막을 수 있는지 확인
				if(!hasSimpleSolution){
					boolean hasSolution = false;
					for(Unit myUnit: (turn == PlayerColor.white) ? whiteUnit: blackUnit){
						ArrayList<int[]> nextUnit = new ArrayList<>();
						nextUnit.addAll(myUnit.next().get(0));
						nextUnit.addAll(myUnit.next().get(1));
						if(myUnit.piece.type != PieceType.king){
							for(int[] nextUnitPosition: nextUnit){
								ArrayList<Unit> myUnits = unitBinder(turn, false);
								ArrayList<Unit> enemyUnits = unitBinder(turn, true);

								Unit tempMyUnit= Unit.findUnit(myUnits, myUnit.piece, myUnit.xpos, myUnit.ypos);
								tempMyUnit.xpos = nextUnitPosition[0];
								tempMyUnit.ypos = nextUnitPosition[1];

								Piece[][] tempMap = mapGenerator(myUnits, enemyUnits);
								if(!isTargeted(king.xpos, king.ypos, turn, tempMap, enemyUnits)){
									hasSolution = true;
									break;
								}
							}
						}
						if(hasSolution)
							break;
					}

					// 모두 돌려봐도 풀릴 수 없다면 체크메이트
					if(!hasSolution){
						message += "MATE";
						checkmate = true;
						end = true;
					}
				}
			} else {
				check = false;
			}
		}
		setStatus(message);
	}

	// 상대 색 반환
	PlayerColor enemyColor(){
		switch (turn){
            case PlayerColor.none -> {
                return PlayerColor.none;
            }
            case PlayerColor.black -> {
				return PlayerColor.white;
			}
			case PlayerColor.white -> {
				return PlayerColor.black;
			}
		}
		return PlayerColor.none;
	}

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
		Unit(Unit other){
			this.xpos = other.xpos;
			this.ypos = other.ypos;
			this.piece = other.piece;
		}
		// 기물의 예상 경로 validation 확인시 bound 설정
		static boolean isInBound(int x, int y){
			return ((0 <= x && x < 8) && (0 <= y && y < 8));
		}
		// piece, x좌표, y좌표를 주면 ArrayList 내에서 이에 해당하는 요소 반환
		static Unit findUnit(ArrayList<Unit> unitlist, Piece piece, int x, int y){
			for(Unit unit: unitlist){
				if(unit.piece.type == piece.type && unit.piece.color == piece.color && unit.xpos == x && unit.ypos == y){
					return unit;
				}
			}
			return null;
		}

		// {{attack 하지 않고 움직일 수 있는 좌표쌍}, {attack 할 수 있는 좌표쌍}}
		ArrayList<ArrayList<int[]>> next(){
			return null;
		}
		// 클래스 내 x,y 좌표 변환 & 화면상 좌표 변환
		void move(int nextX, int nextY){
			setIcon(xpos, ypos, new Piece());
			xpos = nextX;
			ypos = nextY;
			setIcon(xpos, ypos, piece);
		}
		// for Pawn, used for Promotion
		int specialValue(){
			return -1;
		}
		// for Rook and King, used for castling
		boolean isMoved(){
			return false;
		}
		// 특정 위치를 공격할 수 있는지 확인
		boolean canAttack(int x, int y, Piece[][] board){
			return false;
		}

		@Override
		public String toString() {
			if(piece != null && piece.type != null)
				return STR."Objcet \{piece.type} at (\{xpos}, \{ypos})";
			return STR."Objcet at (\{xpos}, \{ypos})";
		}
	}

	// Pawn 클래스
	class Pawn extends Unit{
		boolean isMoved = false;
		int promotionX;
		Pawn(Piece piece, int x, int y){
			super(piece, x, y);
			promotionX = (piece.color == PlayerColor.black) ? 7: 0;
		}

		@Override
		ArrayList<ArrayList<int[]>> next(){
			ArrayList<int[]> move = new ArrayList<>(); // 빈 공간으로 움직일 수 있는 위치들
			ArrayList<int[]> attack = new ArrayList<>(); // 적 기물을 공격할 수 있는 위치들

			// 움직일 수 있는 방향 설정
			int[] moveDirection = (piece.color == PlayerColor.black) ? new int[]{1, 0} : new int[]{-1, 0};

			//빈 공간으로 움직일 수 있는 위치 계산
			int newX = xpos + moveDirection[0];
			int newY = ypos + moveDirection[1];
			if(isInBound(newX, newY) && getIcon(newX, newY).color == PlayerColor.none){
				move.add(new int[]{newX, newY});
				if(!isMoved && getIcon(newX + moveDirection[0], newY + moveDirection[1]).color == PlayerColor.none){
					move.add(new int[]{newX + moveDirection[0], newY + moveDirection[1]});
				}
			}

			// 적 기물 공격 가능 위치 계산
			PlayerColor enemy = (piece.color == PlayerColor.black) ? PlayerColor.white : PlayerColor.black;
			if(isInBound(newX, newY + 1) &&
					(getIcon(newX, newY + 1).color == enemy || VirtualPawn.isVPHere(newX, newY + 1))){
				attack.add(new int[]{newX, newY + 1});
			}
			if(isInBound(newX, newY - 1) &&
					(getIcon(newX, newY - 1).color == enemy || VirtualPawn.isVPHere(newX, newY -1))){
				attack.add(new int[]{newX, newY - 1});
			}

			//반환
			ArrayList<ArrayList<int[]>> returnArray = new ArrayList<>();
			returnArray.add(move);
			returnArray.add(attack);
			return returnArray;
		}

		@Override
		boolean canAttack(int x, int y, Piece[][] map){
			// 움직일 수 있는 방향 설정
			int[] moveDirection = (piece.color == PlayerColor.black) ? new int[]{1, 0} : new int[]{-1, 0};
			int newX = xpos + moveDirection[0];
			int newY = ypos + moveDirection[1];
			if(x == newX && y == newY + 1){
				return true;
			} else if(x == newX && y == newY - 1){
				return true;
			}
			return false;
		}

		@Override
		void move(int nextX, int nextY){
			if(!isMoved && Math.abs(nextX - xpos) == 2){
				VirtualPawn.setVirtualPawn(
						(piece.color == PlayerColor.black) ? nextX - 1 : nextX + 1,
						nextY,
						this
				);
			}
			super.move(nextX, nextY);
			isMoved = true;
		}

		@Override
		int specialValue(){
			return promotionX;
		}

		@Override
		public String toString() {
			return STR."\{piece.color} Pawn at (\{xpos}, \{ypos})";
		}
	}

	// En Passant을 위한 가상 Pawn 클래스
	class VirtualPawn{
		static int xpos = - 1;
		static int ypos = - 1;
		static Pawn actualPawn;
		static void setVirtualPawn(int x, int y, Pawn p){
			xpos = x;
			ypos = y;
			actualPawn = p;
		}
		static void resetVirtualPawn(Pawn dummy){
			xpos = -1;
			ypos = -1;
			actualPawn = dummy;
		}
		static boolean isVPHere(int x, int y){
			return (xpos == x && ypos == y);
		}
		static PlayerColor color(){
			return actualPawn.piece.color;
		}
		static void printVirtualPawn(){
			System.out.println(STR."\{actualPawn}virtual position: (\{xpos}, \{ypos})");
		}
	}

	// Rook 클래스
	class Rook extends Unit{
		boolean isMoved = false;
		Rook(Piece piece, int x, int y){
			super(piece, x, y);
		}
		@Override
		ArrayList<ArrayList<int[]>> next(){
			ArrayList<int[]> move = new ArrayList<>(); // 빈 공간으로 움직일 수 있는 위치들
			ArrayList<int[]> attack = new ArrayList<>(); // 적 기물을 공격할 수 있는 위치들

			int[][] direction = {
					{1, 0},
					{0, 1},
					{-1, 0},
					{0, -1}
			};

			for(int i = 0; i < 4; i++){
				// 방향 지정
				int[] movement = direction[i];

				// 방향 이동 시작
				int nextX = xpos + movement[0];
				int nextY = ypos + movement[1];

				// move에 추가
				while(isInBound(nextX, nextY) && getIcon(nextX, nextY).color == PlayerColor.none){
					move.add(new int[]{nextX, nextY});
					nextX += movement[0];
					nextY += movement[1];
				}

				// attack에 추가
				if(isInBound(nextX, nextY) && getIcon(nextX, nextY). color == enemyColor()){
					attack.add(new int[]{nextX, nextY});
				}
			}

			// Castling Condition Check
			if(!isMoved){
				int nextKingY = (ypos == 0) ? 2: 6;
				if(
						getIcon(xpos, 4).type == PieceType.king &&
						!findUnit((piece.color == PlayerColor.white) ? whiteUnit: blackUnit, getIcon(xpos, 4), xpos, 4).isMoved() &&
						!isTargeted(xpos, nextKingY, piece.color, chessBoardStatus, (piece.color == PlayerColor.black) ? whiteUnit: blackUnit)
				){
					boolean isInCondition = true;
					for(int i = ypos; i != 4; i += (4 - ypos)/Math.abs(4 - ypos)){
						if(i != ypos && getIcon(xpos, i).type != PieceType.none){
							isInCondition = false;
							break;
						}
					}

					if(isInCondition){
						move.add(new int[]{xpos, 4});
					}
				}
			}

			//반환
			ArrayList<ArrayList<int[]>> returnArray = new ArrayList<>();
			returnArray.add(move);
			returnArray.add(attack);
			return returnArray;
		}

		@Override
		boolean canAttack(int x, int y, Piece[][] board){
			int[][] direction = {
					{1, 0},
					{0, 1},
					{-1, 0},
					{0, -1}
			};
			for(int i = 0; i < 4; i++){
				int[] movement = direction[i];
				int nextX = xpos;
				int nextY = ypos;

				// 이동 범위 내에 (x,y)가 있으면 true 반환
				do{
					nextX += movement[0];
					nextY += movement[1];
					if(x == nextX && y == nextY){
						return true;
					}
				} while(isInBound(nextX, nextY) && board[nextY][nextX].color == PlayerColor.none);
			}
			return false;
		}

		@Override
		void move(int newX, int newY){
			super.move(newX, newY);
			isMoved = true;
		}

		@Override
		boolean isMoved(){
			return isMoved;
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
		ArrayList<ArrayList<int[]>> next(){
			ArrayList<int[]> move = new ArrayList<>(); // 빈 공간으로 움직일 수 있는 위치들
			ArrayList<int[]> attack = new ArrayList<>(); // 적 기물을 공격할 수 있는 위치들

			int[][] candidates = {
					{xpos + 1, ypos + 2},
					{xpos + 1, ypos - 2},
					{xpos - 1 ,ypos + 2},
					{xpos - 1 ,ypos - 2},
					{xpos + 2, ypos + 1},
					{xpos + 2, ypos - 1},
					{xpos - 2, ypos + 1},
					{xpos - 2, ypos - 1},
			};

			for(int[] newPosition: candidates){
				if (isInBound(newPosition[0], newPosition[1]) && getIcon(newPosition[0], newPosition[1]).color == PlayerColor.none)
					move.add(newPosition);
				else if (isInBound(newPosition[0], newPosition[1]) && getIcon(newPosition[0], newPosition[1]).color == enemyColor())
					attack.add(newPosition);
			}

			//반환
			ArrayList<ArrayList<int[]>> returnArray = new ArrayList<>();
			returnArray.add(move);
			returnArray.add(attack);
			return returnArray;
		}

		@Override
		boolean canAttack(int x, int y, Piece[][] board){
			int[][] candidates = {
					{xpos + 1, ypos + 2},
					{xpos + 1, ypos - 2},
					{xpos - 1 ,ypos + 2},
					{xpos - 1 ,ypos - 2},
					{xpos + 2, ypos + 1},
					{xpos + 2, ypos - 1},
					{xpos - 2, ypos + 1},
					{xpos - 2, ypos - 1},
			};
			for(int[] newPosition: candidates){
				if (isInBound(newPosition[0], newPosition[1])){
					if(x == newPosition[0] && y == newPosition[1]){
						return true;
					}
				}
			}
			return false;
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
		ArrayList<ArrayList<int[]>> next(){
			ArrayList<int[]> move = new ArrayList<>(); // 빈 공간으로 움직일 수 있는 위치들
			ArrayList<int[]> attack = new ArrayList<>(); // 적 기물을 공격할 수 있는 위치들

			int[][] direction = {
					{1, 1},
					{-1, 1},
					{-1, -1},
					{1, -1}
			};

			for(int i = 0; i < 4; i++){
				// 방향 지정
				int[] movement = direction[i];

				// 방향 이동 시작
				int nextX = xpos + movement[0];
				int nextY = ypos + movement[1];

				// move에 추가
				while(isInBound(nextX, nextY) && getIcon(nextX, nextY).color == PlayerColor.none){
					move.add(new int[]{nextX, nextY});
					nextX += movement[0];
					nextY += movement[1];
				}

				// attack에 추가
				if(isInBound(nextX, nextY) && getIcon(nextX, nextY). color == enemyColor()){
					attack.add(new int[]{nextX, nextY});
				}
			}

			//반환
			ArrayList<ArrayList<int[]>> returnArray = new ArrayList<>();
			returnArray.add(move);
			returnArray.add(attack);
			return returnArray;
		}

		@Override
		boolean canAttack(int x, int y, Piece[][] board){
			int[][] direction = {
					{1, 1},
					{-1, 1},
					{-1, -1},
					{1, -1}
			};
			for(int i = 0; i < 4; i++){
				int[] movement = direction[i];
				int nextX = xpos;
				int nextY = ypos;

				// 이동 범위 내에 (x,y)가 있으면 true 반환
				do{
					nextX += movement[0];
					nextY += movement[1];
					if(x == nextX && y == nextY){
						return true;
					}
				} while(isInBound(nextX, nextY) && board[nextY][nextX].color == PlayerColor.none);
			}
			return false;
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
		ArrayList<ArrayList<int[]>> next(){
			ArrayList<int[]> move = new ArrayList<>(); // 빈 공간으로 움직일 수 있는 위치들
			ArrayList<int[]> attack = new ArrayList<>(); // 적 기물을 공격할 수 있는 위치들

			int[][] direction = {
					{0, 1},
					{0, - 1},
					{1, 0},
					{1, 1},
					{1, - 1},
					{- 1, 0},
					{- 1, 1},
					{- 1, - 1}
			};

			for(int i = 0; i < 8; i++){
				// 방향 지정
				int[] movement = direction[i];

				// 방향 이동 시작
				int nextX = xpos + movement[0];
				int nextY = ypos + movement[1];

				// move에 추가
				while(isInBound(nextX, nextY) && getIcon(nextX, nextY).color == PlayerColor.none){
					move.add(new int[]{nextX, nextY});
					nextX += movement[0];
					nextY += movement[1];
				}

				// attack에 추가
				if(isInBound(nextX, nextY) && getIcon(nextX, nextY). color == enemyColor()){
					attack.add(new int[]{nextX, nextY});
				}
			}

			//반환
			ArrayList<ArrayList<int[]>> returnArray = new ArrayList<>();
			returnArray.add(move);
			returnArray.add(attack);
			return returnArray;
		}

		@Override
		boolean canAttack(int x, int y, Piece[][] board){
			int[][] direction = {
					{0, 1},
					{0, - 1},
					{1, 0},
					{1, 1},
					{1, - 1},
					{- 1, 0},
					{- 1, 1},
					{- 1, - 1}
			};

			for(int i = 0; i < 8; i++){
				int[] movement = direction[i];
				int nextX = xpos;
				int nextY = ypos;


				// 이동 범위 내에 (x,y)가 있으면 true 반환
				do{
					nextX += movement[0];
					nextY += movement[1];
					if(x == nextX && y == nextY){
						return true;
					}
				} while(isInBound(nextX, nextY) && board[nextY][nextX].color == PlayerColor.none);
			}
			return false;
		}

		@Override
		public String toString() {
			return STR."\{piece.color} Queen at (\{xpos}, \{ypos})";
		}
	}

	// King 클래스
	class King extends Unit{
		boolean isMoved;
		King(Piece piece, int x, int y){
			super(piece, x, y);
			isMoved = false;
		}
		@Override
		ArrayList<ArrayList<int[]>> next(){
			ArrayList<int[]> move = new ArrayList<>(); // 빈 공간으로 움직일 수 있는 위치들
			ArrayList<int[]> attack = new ArrayList<>(); // 적 기물을 공격할 수 있는 위치들

			int[][] direction = {
					{0, 1},
					{0, - 1},
					{1, 0},
					{1, 1},
					{1, - 1},
					{- 1, 0},
					{- 1, 1},
					{- 1, - 1}
			};

			for(int i = 0; i < 8; i++) {
				// 방향 지정
				int[] movement = direction[i];

				// 방향 이동 시작
				int nextX = xpos + movement[0];
				int nextY = ypos + movement[1];

				if (isInBound(nextX, nextY) && getIcon(nextX, nextY).color == PlayerColor.none) {
					move.add(new int[]{nextX, nextY});
				} else if (isInBound(nextX, nextY) && getIcon(nextX, nextY).color == enemyColor()) {
					attack.add(new int[]{nextX, nextY});
				}
			}

			//Castling Condition Check
			if(!isMoved){
				// Rook on A
				if(
						getIcon(xpos, 0).type == PieceType.rook &&
						!findUnit((piece.color == PlayerColor.white) ? whiteUnit: blackUnit, getIcon(xpos, 0), xpos, 0).isMoved() &&
						!isTargeted(xpos, 2, piece.color, chessBoardStatus, (piece.color == PlayerColor.black) ? whiteUnit: blackUnit)
				){
					boolean isIncondition = true;
					for(int i = 1; i < 4; i++){
						if(getIcon(xpos, i).type != PieceType.none){
							isIncondition = false;
							break;
						}
					}

					if(isIncondition){
						move.add(new int[]{xpos, 0});
					}
				}

				// Rook on B
				if(
						getIcon(xpos, 7).type == PieceType.rook &&
						!findUnit((piece.color == PlayerColor.white) ? whiteUnit: blackUnit, getIcon(xpos, 7), xpos, 7).isMoved() &&
						!isTargeted(xpos, 6, piece.color,chessBoardStatus, (piece.color == PlayerColor.black) ? whiteUnit: blackUnit)
				){
					boolean isIncondition = true;
					for(int i = 5; i < 7; i++){
						if(getIcon(xpos, i).type != PieceType.none){
							isIncondition = false;
							break;
						}
					}

					if(isIncondition){
						move.add(new int[]{xpos, 7});
					}
				}
			}

			//반환
			ArrayList<ArrayList<int[]>> returnArray = new ArrayList<>();
			returnArray.add(move);
			returnArray.add(attack);
			return returnArray;
		}

		@Override
		boolean canAttack(int x, int y, Piece[][] board){
			int[][] direction = {
					{0, 1},
					{0, - 1},
					{1, 0},
					{1, 1},
					{1, - 1},
					{- 1, 0},
					{- 1, 1},
					{- 1, - 1}
			};
			for(int i = 0; i < 8; i++) {
				int[] movement = direction[i];
				int nextX = xpos + movement[0];
				int nextY = ypos + movement[1];

				if(x == nextX && y == nextY){
					return true;
				}
			}
			return false;
		}

		@Override
		void move(int newX, int newY){
			super.move(newX, newY);
			isMoved = true;
		}

		@Override
		boolean isMoved(){
			return isMoved;
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
	// piece, x좌표, y좌표를 주면 ArrayList 내에서 이에 해당하는 unit 제거
	void deleteUnit(ArrayList<Unit> unitlist, Piece piece, int x, int y, boolean handleIcon){
		unitlist.removeIf(unit -> unit.piece.type == piece.type && unit.piece.color == piece.color && unit.xpos == x && unit.ypos == y);
		if(handleIcon)
			setIcon(x, y, new Piece());
	}
	// x 좌표, y 좌표, color를 받았을 때 해당 위치를 적이 공격할 수 있는지 확인
	boolean isTargeted(int x, int y, PlayerColor p, Piece[][] map, ArrayList<Unit> enemyUnits){
		for(Unit enemyUnit: enemyUnits){
			if (enemyUnit.canAttack(x, y, map))
				return true;
		}
		return false;
	}

	// 클릭한 위치가 단순 이동 위치인지, 공격 위치인지, 아무것도 아닌지 반환
	ClickAction nextAction(int[] position, ArrayList<ArrayList<int[]>> next){
		if(next == null || next.isEmpty()){
			return ClickAction.None;
		}
		// 선택한 위치가 단순 이동 위치
		if(next.get(0).stream().anyMatch(point -> Arrays.equals(point, position))){
			return ClickAction.Move;
		} else if (next.get(1).stream().anyMatch(point -> Arrays.equals(point, position))){ // 공격위치
			return ClickAction.Attack;
		} else { // 빈 위치
			return ClickAction.None;
		}
	}

	// 이전 action에서 누른 타일 저장
	Piece prevTile = null;
	int prevX;
	int prevY;
	// 이전에 내 기물을 선택했다면, 기물이 움직일 수 있는 위치 저장
	ArrayList<ArrayList<int[]>> prevNext;
	// 하이라이트된 타일 저장
	ArrayList<int[]> highlighted = new ArrayList<>();

	class ButtonListener implements ActionListener{
		int x;
		int y;
		ButtonListener(int x, int y){
			this.x = x;
			this.y = y;
		}
		// (x, y) is where the click event occured
		public void actionPerformed(ActionEvent e) {
			// 하이라이트 제거
			for (int[] highlightedTile : highlighted) {
				unmarkPosition(highlightedTile[0], highlightedTile[1]);
			}
			highlighted.clear();

			if(!end){
				if(getIcon(x, y).color == turn){ // 내 기물 클릭
					switch (nextAction(new int[]{x, y}, prevNext)){
						case ClickAction.Move: {
							// castling
							Unit kingUnit = (getIcon(x, y).type == PieceType.king) ? Unit.findUnit(
									(turn == PlayerColor.white) ? whiteUnit: blackUnit,
									getIcon(x, y), x, y
							): Unit.findUnit(
									(turn == PlayerColor.white) ? whiteUnit: blackUnit,
									getIcon(x, 4), x, 4
							);
							Unit rookUnit = (getIcon(x, y).type == PieceType.king) ? Unit.findUnit(
									(turn == PlayerColor.white) ? whiteUnit: blackUnit,
									prevTile, prevX, prevY
							): Unit.findUnit(
									(turn == PlayerColor.white) ? whiteUnit: blackUnit,
									getIcon(x, y), x, y
							);

							kingUnit.move(x, (rookUnit.ypos == 0) ? 2: 6);
							rookUnit.move(x, (kingUnit.ypos == 2) ? 3: 5);

							if(prevNext != null)
								prevNext.clear();
							changeTurn();
							break;
						}
						case ClickAction.Attack: {
							// which may not happen
							System.out.println("Unexpected Operation: my unit attacks my unit");
							break;
						}
						case ClickAction.None: {
							// prevNext 업데이트
							if(prevNext != null)
								prevNext.clear();
							Unit selected = (turn == PlayerColor.white) ? Unit.findUnit(whiteUnit, getIcon(x, y), x, y):
									Unit.findUnit(blackUnit, getIcon(x, y), x, y);
							prevNext = selected.next();

							// check 상황일시 제약 생김
							if(check){
								Unit king = null;
								for(Unit unit: (turn == PlayerColor.white) ? whiteUnit: blackUnit){
									if(unit.piece.type == PieceType.king){
										king = unit;
									}
								}
								for(ArrayList<int[]> nextStep: prevNext){
									Unit finalKing = king;
									nextStep.removeIf(position -> !canRelieveCheck(position[0], position[1], turn, selected, finalKing));
								}
							}

							// 하이라이트 추가
							if(prevNext != null && !prevNext.isEmpty()){
								for(ArrayList<int[]> candidate: prevNext){
									highlighted.addAll(candidate);
								}
								for(int[] highlightTile: highlighted){
									markPosition(highlightTile[0], highlightTile[1]);
								}
							}
							break;
						}
					}
				} else if (getIcon(x, y).color == enemyColor()){ // 상대 기물 클릭
					switch (nextAction(new int[]{x, y}, prevNext)){
						case ClickAction.Move: {
							// Which may not happen
							System.out.println("Unexpected Action: Moving enemy's unit");
							break;
						}
						case ClickAction.Attack: {
							deleteUnit((turn == PlayerColor.white) ? blackUnit: whiteUnit, getIcon(x, y), x, y, true);
							Unit selected = (turn == PlayerColor.white) ? Unit.findUnit(whiteUnit, prevTile, prevX, prevY):
									Unit.findUnit(blackUnit, prevTile, prevX, prevY);
							selected.move(x, y);
							if(prevNext != null)
								prevNext.clear();

							// Promotion
							if (selected.piece.type == PieceType.pawn && selected.xpos == selected.specialValue()){
								int x = selected.xpos;
								int y = selected.ypos;
								Piece p = selected.piece;

								deleteUnit((turn == PlayerColor.white) ? whiteUnit: blackUnit, p, x, y, true);
								setIcon(x, y, new Piece(turn, PieceType.queen));
								((turn == PlayerColor.white) ? whiteUnit: blackUnit).add(new Queen(getIcon(x, y), x, y));
							}

							changeTurn();
							break;
						}
						case ClickAction.None: {
							// No action Needed
							if(prevNext != null)
								prevNext.clear();
							break;
						}
					}
				} else { // 빈 타일 클릭
					switch (nextAction(new int[]{x, y}, prevNext)){
						case ClickAction.Move: {
							Unit selected = (turn == PlayerColor.white) ? Unit.findUnit(whiteUnit, prevTile, prevX, prevY):
									Unit.findUnit(blackUnit, prevTile, prevX, prevY);
							selected.move(x, y);

							// Promotion
							if (selected.piece.type == PieceType.pawn && selected.xpos == selected.specialValue()){
								int x = selected.xpos;
								int y = selected.ypos;
								Piece p = selected.piece;

								deleteUnit((turn == PlayerColor.white) ? whiteUnit: blackUnit, p, x, y, true);
								setIcon(x, y, new Piece(turn, PieceType.queen));
								((turn == PlayerColor.white) ? whiteUnit: blackUnit).add(new Queen(getIcon(x, y), x, y));
							}

							if(prevNext != null)
								prevNext.clear();
							changeTurn();
							break;
						}
						case ClickAction.Attack: {
							// en passant
							deleteUnit((turn == PlayerColor.white) ? blackUnit: whiteUnit,
									VirtualPawn.actualPawn.piece, VirtualPawn.actualPawn.xpos, VirtualPawn.actualPawn.ypos, true);
							Unit selected = (turn == PlayerColor.white) ? Unit.findUnit(whiteUnit, prevTile, prevX, prevY):
									Unit.findUnit(blackUnit, prevTile, prevX, prevY);
							selected.move(x, y);
							if(prevNext != null)
								prevNext.clear();

							changeTurn();
							break;
						}
						case ClickAction.None: {
							// no action needed
							if(prevNext != null)
								prevNext.clear();
							break;
						}
					}
				}
				prevTile = getIcon(x, y);
				prevX = x;
				prevY = y;

				if(VirtualPawn.color() == turn){
					Pawn dummy = new Pawn(new Piece(), -1, -1);
					VirtualPawn.resetVirtualPawn(dummy);
				}
			}
		}
	}

	void onInitiateBoard(){
		blackUnit.clear();
		whiteUnit.clear();
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
		// 흰색 기물들에 대한 정보 저장
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
		// En passant 용 VirtualPawn 초기화
		Pawn dummy = new Pawn(new Piece(), -1, -1);
		VirtualPawn.resetVirtualPawn(dummy);
		// 마커 초기화
		check = false;
		checkmate = false;
		end = false;
		// 흰색 턴 시작
		turn = PlayerColor.none;
		changeTurn();
	}
}