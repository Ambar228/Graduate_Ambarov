import React from 'react';
import {Carousel} from "react-bootstrap";

const Sponsors = ({sponsors}) => {

    return (
        <>
            <Carousel slide={true} interval={1000}>
                {
                    typeof(sponsors) === 'object' && sponsors.map(sponsor => (
                        <Carousel.Item>
                            <img
                                className="d-block mx-auto"
                                style={{ width: '500px', height: '500px'}}
                                src={`data:image/png;base64,${sponsor.logo}`}
                                alt="First slide"
                            />

                        </Carousel.Item>
                    ))
                }
            </Carousel>
        </>
    )

}

export default Sponsors;