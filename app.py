import streamlit as st
import qrcode
from PIL import Image
from io import BytesIO

st.set_page_config(
    page_title="QR Code Generator",
    page_icon="📷",
    layout='centered',
    initial_sidebar_state='auto'
)

def generate_qrcode(content):
    qr = qrcode.QRCode(
        version=1,
        error_correction=qrcode.constants.ERROR_CORRECT_L,
        box_size=10,
        border=4,
    )

    qr.add_data(content)
    qr.make(fit=True)
    img = qr.make_image(fill_color="black", back_color="white")

    img_bytes = BytesIO()
    img.save(img_bytes, format="PNG")
    return img_bytes.getvalue()

def main():
    st.title("QR Code Generator")

    content = st.text_input("Enter content for QR code:")

    if st.button("Generate QR Code"):
        if content:
            qr_code_bytes = generate_qrcode(content)

            st.image(qr_code_bytes, caption="Generated QR Code", use_column_width=True, output_format="PNG")

            st.download_button(
                label="Download QR Code",
                data=qr_code_bytes,
                file_name="generated_qrcode.png",
                mime="image/png",
            )

        else:
            st.warning("Please enter content for the QR code.")

if __name__ == "__main__":
    main()
