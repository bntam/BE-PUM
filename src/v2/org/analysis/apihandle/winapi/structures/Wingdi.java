package v2.org.analysis.apihandle.winapi.structures;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinDef.BYTE;

/**
 * 
 * @author Yen Nguyen
 *
 */
public interface Wingdi {
	public static class PALETTEENTRY extends Structure {
		public BYTE peRed;
		public BYTE peGreen;
		public BYTE peBlue;
		public BYTE peFlags;

		protected List<String> getFieldOrder() {
			return Arrays.asList(new String[] { "peRed", "peGreen", "peBlue", "peFlags" });
		}

		public static class ByReference extends PALETTEENTRY implements Structure.ByReference {
			public ByReference() {
			}

			public ByReference(Pointer memory) {
				super(memory);
			}
		}

		public PALETTEENTRY() {

		}

		public PALETTEENTRY(Pointer memory) {
			super(memory);
			read();
		}
	}
}
